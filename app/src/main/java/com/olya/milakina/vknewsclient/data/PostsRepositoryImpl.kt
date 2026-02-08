package com.olya.milakina.vknewsclient.data

import android.content.Context
import com.olya.milakina.vknewsclient.data.model.getCount
import com.olya.milakina.vknewsclient.data.model.toDomain
import com.olya.milakina.vknewsclient.domain.Post
import com.olya.milakina.vknewsclient.domain.StatisticItem
import com.olya.milakina.vknewsclient.domain.StatisticType
import kotlinx.coroutines.delay

class PostsRepositoryImpl(context: Context) : PostRepository {

    private val api = ApiFactory.apiService
    private val _posts = mutableListOf<Post>()
    override val posts: List<Post>
        get() = _posts.toList()

    private var _hasNext: Boolean = true
    override val hasNext: Boolean
        get() = _hasNext
    private var currentPage: Int = 1
    private var currentPageId: Int = 1

    private val prefs = SecurePrefs(context)

    private val deletedIds: MutableSet<Long> =
        prefs.getSetLong(SecurePrefs.DELETED_IDS).toMutableSet()

    override suspend fun loadPosts() {
        if (_hasNext) {
            val newPosts = api.getPosts(
                page = currentPage,
                pageSize = PAGE_SIZE
            ).toDomain(count = currentPageId)
            val modifiedPosts = newPosts.toMutableList().apply {
                removeIf { deletedIds.contains(it.id) }
            }
            _posts.addAll(modifiedPosts)
            currentPage++
            currentPageId += newPosts.size
            _hasNext = newPosts.size == PAGE_SIZE
        }
    }

    override suspend fun changeLikeStatus(post: Post) {
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
        deletedIds.add(post.id)
        prefs.putSetLong(SecurePrefs.DELETED_IDS, deletedIds)
        _posts.removeIf { deletedIds.contains(it.id) }
    }

    companion object {
        private const val PAGE_SIZE = 15
    }
}
