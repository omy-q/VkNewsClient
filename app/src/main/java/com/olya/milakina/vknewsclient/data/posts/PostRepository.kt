package com.olya.milakina.vknewsclient.data.posts

import com.olya.milakina.vknewsclient.domain.Post
import com.olya.milakina.vknewsclient.PaginationState
import kotlinx.coroutines.flow.StateFlow

interface PostRepository {

    val postsFlow: StateFlow<PaginationState<Post>>

    suspend fun loadNextPosts()

    suspend fun changeLikeStatus(post: Post)

    suspend fun deletePost(post: Post)
}