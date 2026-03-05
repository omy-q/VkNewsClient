package com.olya.milakina.vknewsclient.domain.repositories

import com.olya.milakina.vknewsclient.domain.entities.PaginationState
import com.olya.milakina.vknewsclient.domain.entities.Post
import kotlinx.coroutines.flow.StateFlow

internal interface PostRepository {

    fun loadPosts(): StateFlow<PaginationState<Post>>

    suspend fun loadNextPosts()

    suspend fun changeLikeStatus(post: Post)

    suspend fun deletePost(post: Post)
}