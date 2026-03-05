package com.olya.milakina.vknewsclient.data.comments.model

import com.google.gson.annotations.SerializedName
import com.olya.milakina.vknewsclient.data.toDomainDate
import com.olya.milakina.vknewsclient.domain.PostComment
import java.util.UUID

internal data class CommentDto(
    @SerializedName("author") val author: String?,
    @SerializedName("title") val text: String?,
    @SerializedName("publishedAt") val publicationDate: String
)

internal fun CommentDto.toDomain(authorIcon: String?): PostComment {
    return PostComment(
        id = UUID.randomUUID(),
        authorName = author ?: "",
        authorIcon = authorIcon,
        commentText = text ?: "",
        publicationDate = publicationDate.toDomainDate()
    )
}
