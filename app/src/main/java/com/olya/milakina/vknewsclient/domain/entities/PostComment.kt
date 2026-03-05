package com.olya.milakina.vknewsclient.domain.entities

import java.util.UUID

internal data class PostComment(
    val id: UUID,
    val authorName: String,
    val authorIcon: String?,
    val commentText: String,
    val publicationDate: String
)
