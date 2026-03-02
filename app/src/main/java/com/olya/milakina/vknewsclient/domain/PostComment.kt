package com.olya.milakina.vknewsclient.domain

data class PostComment(
    val id: Long,
    val authorName: String,
    val authorIcon: String?,
    val commentText: String,
    val publicationDate: String
)
