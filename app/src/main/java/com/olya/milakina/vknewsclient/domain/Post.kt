package com.olya.milakina.vknewsclient.domain

data class Post(
    val id: Long,
    val title: String,
    val titleIcon: String?,
    val publicationDate: String,
    val contentText: String,
    val contentImage: String?,
    val statistics: List<StatisticItem>,
    val isLiked: Boolean
)
