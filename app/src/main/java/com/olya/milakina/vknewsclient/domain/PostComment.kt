package com.olya.milakina.vknewsclient.domain

import java.util.UUID

data class PostComment(
    val id: UUID,
    val authorName: String,
    val authorIcon: String?,
    val commentText: String,
    val publicationDate: String
)
