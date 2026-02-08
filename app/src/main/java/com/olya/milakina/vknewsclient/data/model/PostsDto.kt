package com.olya.milakina.vknewsclient.data.model

import com.google.gson.annotations.SerializedName
import com.olya.milakina.vknewsclient.domain.Post

data class PostsDto(
    @SerializedName("status") val status: String?,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val posts: List<PostDto>?
)

fun PostsDto.toDomain(count: Int): List<Post> {
    return this.posts?.mapIndexed { index, post ->
        post.toDomain(id = ((index * count).toLong()))
    } ?: listOf()
}
