package com.olya.milakina.vknewsclient.domain

data class StatisticItem(
    val type: StatisticType,
    val count: Int = 0
)

enum class StatisticType {
    Views, Comments, Shares, Likes
}
