package com.olya.milakina.vknewsclient.data

import com.olya.milakina.vknewsclient.domain.Post

interface PostRepository {

    val posts: List<Post>
    val hasNext: Boolean

    suspend fun loadPosts()

    suspend fun changeLikeStatus(post: Post)

    suspend fun deletePost(post: Post)
}
