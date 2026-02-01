package com.example.vknewsclient.domain

data class VkPost(
    val id: Int,
    val title: String = "уволено",
    val titleIcon: String? = null,
    val publicationDate: String = "14:00",
    val contentText: String = "hjb j,yf,j cghvjbk chgfjk yfjhgio kdfguhkjl cgtfhyji nhgfygjuhijo fcghjuji cghfjk",
    val contentImage: String? = null,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(StatisticType.Views, 206),
        StatisticItem(StatisticType.Shares, 206),
        StatisticItem(StatisticType.Comments, 11),
        StatisticItem(StatisticType.Likes, 491),
    )
)
