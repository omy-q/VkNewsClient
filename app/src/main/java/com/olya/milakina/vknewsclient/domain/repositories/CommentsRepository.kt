package com.olya.milakina.vknewsclient.domain.repositories

import com.olya.milakina.vknewsclient.domain.entities.PaginationState
import com.olya.milakina.vknewsclient.domain.entities.PostComment
import com.olya.milakina.vknewsclient.domain.entities.Post
import kotlinx.coroutines.flow.Flow

internal interface CommentsRepository {

    fun loadComments(post: Post): Flow<PaginationState<PostComment>>
    suspend fun loadNextComments()
}
