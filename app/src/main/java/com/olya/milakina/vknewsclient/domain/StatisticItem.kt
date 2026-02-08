package com.olya.milakina.vknewsclient.domain

data class StatisticItem(
    val type: StatisticType,
    val count: Int
)

enum class StatisticType {
    Views, Comments, Shares, Likes
}
