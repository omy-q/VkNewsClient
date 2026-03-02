package com.olya.milakina.vknewsclient.data.comments

import com.olya.milakina.vknewsclient.data.ApiFactory
import com.olya.milakina.vknewsclient.data.comments.model.toDomain
import com.olya.milakina.vknewsclient.domain.Post
import com.olya.milakina.vknewsclient.domain.PostComment

class CommentsRepositoryImpl : CommentsRepository {

    private val api = ApiFactory.apiService

    private val _comments = mutableListOf<PostComment>()
    override val comments: List<PostComment>
        get() = _comments.toList()

    private var _hasNext: Boolean = true
    override val hasNext: Boolean
        get() = _hasNext

    private var currentPage: Int = 1
    private var currentPageId: Int = 1

    override suspend fun loadComments(post: Post) {
        if (_hasNext) {
            val newPosts = api.getComments(
                page = currentPage,
                pageSize = PAGE_SIZE,
                query = post.authorName ?: post.title
            ).toDomain(
                count = currentPageId,
                authorIcon = post.titleIcon
            )
            _comments.addAll(newPosts)
            currentPage++
            currentPageId += newPosts.size
            _hasNext = newPosts.size == PAGE_SIZE
        }
    }

    companion object {
        private const val PAGE_SIZE = 15
    }
}
