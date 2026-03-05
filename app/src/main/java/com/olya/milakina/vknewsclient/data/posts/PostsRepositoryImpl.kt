package com.olya.milakina.vknewsclient.data.posts

import com.olya.milakina.vknewsclient.data.ApiService
import com.olya.milakina.vknewsclient.data.getCount
import com.olya.milakina.vknewsclient.data.posts.model.toDomain
import com.olya.milakina.vknewsclient.domain.entities.PaginationState
import com.olya.milakina.vknewsclient.domain.entities.Post
import com.olya.milakina.vknewsclient.domain.entities.StatisticItem
import com.olya.milakina.vknewsclient.domain.entities.StatisticType
import com.olya.milakina.vknewsclient.domain.repositories.PostRepository
import com.olya.milakina.vknewsclient.mergeWith
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

internal class PostsRepositoryImpl @Inject constructor(
    private val api: ApiService
) : PostRepository {
    private val deletedIds: MutableSet<String> = mutableSetOf()

    private var currentPage: Int = 1
    private var hasNext: Boolean = true

    private val _posts = mutableListOf<Post>()
    private val posts get() = _posts.toList()

    private val scope = CoroutineScope(Dispatchers.IO)
    private val loadedPostsFlow = flow {
        nextDataEvents.emit(Unit)
        nextDataEvents.collect {
            if (posts.isEmpty()) {
                emit(PaginationState.FirstPageLoading())
            }

            if (hasNext) {
                if (posts.isNotEmpty()) {
                    emit(PaginationState.NextPageLoading())
                }

                val newPosts = api.getPosts(
                    page = currentPage,
                    pageSize = PAGE_SIZE
                ).toDomain()

                val modifiedPosts = newPosts.toMutableList().apply {
                    removeIf { deletedIds.contains(it.id.toString()) }
                }

                _posts.addAll(modifiedPosts)
                currentPage++
                hasNext = newPosts.size == PAGE_SIZE || newPosts.size == PAGE_SIZE - 1
                emit(PaginationState.PageLoaded(posts))
            } else {
                emit(PaginationState.AllPagesLoaded(posts))
            }
        }
    }.retry(RETRIES_COUNT) {
        delay(RETRY_TIMEOUT_MILLIS)
        true
    }.catch {
        emit(PaginationState.FailureLoading(error = it))
    }
    private val changePostsFlow = MutableSharedFlow<PaginationState<Post>>()
    private val nextDataEvents = MutableSharedFlow<Unit>(replay = 1)

    private val postsFlow = loadedPostsFlow.mergeWith(changePostsFlow).stateIn(
        scope = scope,
        started = SharingStarted.Lazily,
        initialValue = PaginationState.FirstPageLoading()
    )

    override fun loadPosts() = postsFlow

    override suspend fun loadNextPosts() {
        nextDataEvents.emit(Unit)
    }

    override suspend fun changeLikeStatus(post: Post) {
        val newState = when (postsFlow.value) {
            is PaginationState.PageLoaded<*> -> {
                processChangeLikeStatus(post)
                PaginationState.PageLoaded(data = posts)
            }

            is PaginationState.AllPagesLoaded<*> -> {
                processChangeLikeStatus(post)
                PaginationState.AllPagesLoaded(data = posts)
            }

            else -> postsFlow.value
        }
        changePostsFlow.emit(newState)
    }

    private suspend fun processChangeLikeStatus(post: Post) {
        delay(200)
        val newCount = getCount()
        val newStatistics = post.statistics.toMutableList().apply {
            removeIf { it.type == StatisticType.Likes }
            add(StatisticItem(type = StatisticType.Likes, count = newCount))
        }

        val newPost = post.copy(statistics = newStatistics, isLiked = !post.isLiked)
        val index = _posts.indexOf(post)
        _posts[index] = newPost
    }

    override suspend fun deletePost(post: Post) {
        val newState = when (postsFlow.value) {
            is PaginationState.PageLoaded<*> -> {
                processDeletePost(post)
                PaginationState.PageLoaded(data = posts)
            }

            is PaginationState.AllPagesLoaded<*> -> {
                processDeletePost(post)
                PaginationState.AllPagesLoaded(data = posts)
            }

            else -> postsFlow.value
        }
        changePostsFlow.emit(newState)
    }

    private suspend fun processDeletePost(post: Post) {
        delay(1000)
        deletedIds.add(post.id.toString())
        _posts.removeIf { deletedIds.contains(it.id.toString()) }
    }

    companion object {
        private const val PAGE_SIZE = 8
        private const val RETRIES_COUNT = 2L
        private const val RETRY_TIMEOUT_MILLIS = 3000L
    }
}
