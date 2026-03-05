package com.olya.milakina.vknewsclient.data.comments

import com.olya.milakina.vknewsclient.PaginationState
import com.olya.milakina.vknewsclient.domain.Post
import com.olya.milakina.vknewsclient.domain.PostComment
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {

    fun loadComments(post: Post): Flow<PaginationState<PostComment>>
    suspend fun loadNextComments()
}