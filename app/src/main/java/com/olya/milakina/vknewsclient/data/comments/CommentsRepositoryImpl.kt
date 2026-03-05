package com.olya.milakina.vknewsclient.data.comments

import com.olya.milakina.vknewsclient.data.ApiService
import com.olya.milakina.vknewsclient.data.comments.model.toDomain
import com.olya.milakina.vknewsclient.domain.entities.PaginationState
import com.olya.milakina.vknewsclient.domain.entities.Post
import com.olya.milakina.vknewsclient.domain.entities.PostComment
import com.olya.milakina.vknewsclient.domain.repositories.CommentsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import javax.inject.Inject

internal class CommentsRepositoryImpl @Inject constructor(
    private val api: ApiService
) : CommentsRepository {
    private var currentPage: Int = 1
    private var hasNext: Boolean = true

    private val _comments = mutableListOf<PostComment>()
    private val comments get() = _comments.toList()

    private val nextDataEvents = MutableSharedFlow<Unit>(replay = 1)

    override fun loadComments(post: Post) = flow {
        nextDataEvents.emit(Unit)
        nextDataEvents.collect {
            if (comments.isEmpty()) {
                emit(PaginationState.FirstPageLoading())
            }

            if (hasNext) {
                if (comments.isNotEmpty()) {
                    emit(PaginationState.NextPageLoading())
                }

                val newComments = api.getComments(
                    page = currentPage,
                    pageSize = PAGE_SIZE,
                    query = post.authorName ?: post.title
                ).toDomain(post.titleIcon)

                _comments.addAll(newComments)
                currentPage++
                hasNext = newComments.size == PAGE_SIZE
                emit(PaginationState.PageLoaded(comments))
            } else {
                emit(PaginationState.AllPagesLoaded(comments))
            }
        }
    }.retry(RETRIES_COUNT) {
        delay(RETRY_TIMEOUT_MILLIS)
        true
    }.catch {
        emit(PaginationState.FailureLoading(it))
    }

    override suspend fun loadNextComments() {
        nextDataEvents.emit(Unit)
    }

    companion object {
        private const val PAGE_SIZE = 8
        private const val RETRIES_COUNT = 2L
        private const val RETRY_TIMEOUT_MILLIS = 3000L
    }
}
