package com.olya.milakina.vknewsclient.domain.entities

internal data class StatisticItem(
    val type: StatisticType,
    val count: Int
)

internal enum class StatisticType {
    Views, Comments, Shares, Likes
}
