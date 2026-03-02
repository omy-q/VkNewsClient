package com.olya.milakina.vknewsclient.data.comments.model

import com.google.gson.annotations.SerializedName
import com.olya.milakina.vknewsclient.domain.PostComment

internal data class CommentsDto(
    @SerializedName("status") val status: String?,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val posts: List<CommentDto>?
)

internal fun CommentsDto.toDomain(count: Int, authorIcon: String?): List<PostComment> {
    return this.posts?.mapIndexed { index, comment ->
        comment.toDomain(
            id = ((index * count).toLong()),
            authorIcon = authorIcon
        )
    } ?: listOf()
}
