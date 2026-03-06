package com.olya.milakina.vknewsclient.domain.entities

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
internal data class Post(
    val id: UUID,
    val authorName: String?,
    val title: String,
    val titleIcon: String?,
    val publicationDate: String,
    val contentText: String,
    val contentImage: String?,
    val statistics: List<StatisticItem>,
    val isLiked: Boolean
)
