package com.olya.milakina.vknewsclient.data.posts.model

import com.google.gson.annotations.SerializedName
import com.olya.milakina.vknewsclient.data.getCount
import com.olya.milakina.vknewsclient.data.toDomainDate
import com.olya.milakina.vknewsclient.domain.entities.Post
import com.olya.milakina.vknewsclient.domain.entities.StatisticItem
import com.olya.milakina.vknewsclient.domain.entities.StatisticType
import java.util.UUID
import kotlin.random.Random

internal data class PostDto(
    @SerializedName("author") val author: String?,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("urlToImage") val image: String?,
    @SerializedName("publishedAt") val publicationDate: String
)

internal fun PostDto.toDomain(): Post {
    return Post(
        id = UUID.randomUUID(),
        authorName = author,
        title = this.title,
        titleIcon = getTitleIcon(),
        publicationDate = this.publicationDate.toDomainDate(),
        contentText = this.description ?: "",
        contentImage = this.image,
        statistics = listOf(
            StatisticItem(type = StatisticType.Views, count = getCount()),
            StatisticItem(type = StatisticType.Comments, count = getCount()),
            StatisticItem(type = StatisticType.Shares, count = getCount()),
            StatisticItem(type = StatisticType.Likes, count = getCount())
        ),
        isLiked = getIsLiked()
    )
}

private fun getIsLiked(): Boolean = Random.nextBoolean()

private fun getTitleIcon(): String? {
    val avatars = listOf(
        "https://media.istockphoto.com/id/837898820/ru/%D1%84%D0%BE%D1%82%D0%BE/%D0%B7%D0%BE%D0%BB%D0%BE%D1%82%D0%BE%D0%B9-%D1%80%D0%B5%D1%82%D1%80%D0%B8%D0%B2%D0%B5%D1%80-%D1%81%D0%B8%D0%B4%D1%8F%D1%89%D0%B8%D0%B9-%D0%BD%D0%B0-%D1%84%D0%B5%D1%80%D0%BC%D0%B5.jpg?s=612x612&w=0&k=20&c=6AGcNz971vtGZwUZnUWQ_sIU2vN-3cCBhKz2NHeMGUk=",
        "https://media.istockphoto.com/id/497385652/ru/%D1%84%D0%BE%D1%82%D0%BE/%D1%81%D0%BE%D0%B1%D0%B0%D0%BA%D0%B0-%D0%BD%D0%B0-%D0%BA%D1%80%D0%BE%D0%B2%D0%B0%D1%82%D0%B8.jpg?s=612x612&w=0&k=20&c=aWikuQ0gOMJM8D9lTdpyxCCfXDbXj65x0O-z8MoNORo=",
        "https://media.istockphoto.com/id/2164450344/ru/%D1%84%D0%BE%D1%82%D0%BE/%D0%BA%D1%80%D1%83%D0%BF%D0%BD%D1%8B%D0%B9-%D0%BF%D0%BB%D0%B0%D0%BD-%D1%87%D0%B5%D1%80%D0%BD%D0%BE%D0%B3%D0%BE-%D0%BB%D0%B0%D0%B1%D1%80%D0%B0%D0%B4%D0%BE%D1%80%D0%B0-%D1%80%D0%B5%D1%82%D1%80%D0%B8%D0%B2%D0%B5%D1%80%D0%B0-%D0%BF%D0%BB%D0%B0%D0%B2%D0%B0%D1%8E%D1%89%D0%B5%D0%B3%D0%BE-%D0%B2-%D0%B2%D0%BE%D0%B4%D0%B5.jpg?s=612x612&w=0&k=20&c=wADrSJnS0Twu7BG5Ug-ZYClprqowOq5iv3PVealDgD0=",
        "https://media.istockphoto.com/id/891344854/ru/%D1%84%D0%BE%D1%82%D0%BE/%D1%81%D0%BE%D0%B1%D0%B0%D0%BA%D0%B0-%D0%BD%D1%8B%D1%80%D1%8F%D0%B5%D1%82-%D0%BF%D0%BE%D0%B4-%D0%B2%D0%BE%D0%B4%D1%83-%D0%B2-%D0%B1%D0%B0%D1%81%D1%81%D0%B5%D0%B9%D0%BD%D0%B5.jpg?s=612x612&w=0&k=20&c=my3lWT_4BQ9mfIQg3gv3w3AC0uXgqypY3K8CzNQ35QA=",
        "https://media.istockphoto.com/id/1277453154/ru/%D1%84%D0%BE%D1%82%D0%BE/%D0%B7%D0%BE%D0%BB%D0%BE%D1%82%D0%BE%D0%B9-%D1%80%D0%B5%D1%82%D1%80%D0%B8%D0%B2%D0%B5%D1%80-%D1%81%D0%BE%D0%B1%D0%B0%D0%BA%D0%B0-%D0%B2-%D0%BF%D0%B0%D1%80%D0%B8%D0%BA%D0%BC%D0%B0%D1%85%D0%B5%D1%80%D1%81%D0%BA%D0%BE%D0%B9-%D0%BF%D1%80%D0%B8%D0%BD%D0%B8%D0%BC%D0%B0%D0%B5%D1%82-%D0%B4%D1%83%D1%88.jpg?s=612x612&w=0&k=20&c=EKB5pLFdbaX-Efigj5y4kEDFeG0cz3xIM3QnIMlaaD0=",
        "https://media.istockphoto.com/id/1139473985/ru/%D1%84%D0%BE%D1%82%D0%BE/%D0%BA%D1%80%D1%83%D0%BF%D0%BD%D1%8B%D0%BC-%D0%BF%D0%BB%D0%B0%D0%BD%D0%BE%D0%BC-%D1%81%D0%BC%D0%B5%D1%88%D0%BD%D1%8B%D0%B5-%D0%B3%D0%BB%D0%B0%D0%B7%D0%B0-%D1%81%D0%BE%D0%B1%D0%B0%D0%BA%D0%B8-%D0%BB%D0%B0%D0%B1%D1%80%D0%B0%D0%B4%D0%BE%D1%80%D0%B0-%D0%B8%D0%B7%D0%BE%D0%BB%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%BD%D0%B0%D1%8F-%D1%81%D1%82%D1%83%D0%B4%D0%B8%D1%8F-%D1%81%D0%BD%D1%8F%D1%82%D0%B0%D1%8F-%D0%BD%D0%B0-%D0%B1%D0%B5%D0%BB%D0%BE%D0%BC.jpg?s=612x612&w=0&k=20&c=_JOra5KYPlrQQdrIXNHgUNtgRdqNqM_n_V6mS-J3HrI=",
        "https://media.istockphoto.com/id/1285245204/ru/%D1%84%D0%BE%D1%82%D0%BE/%D0%BE%D0%B7%D0%BE%D1%80%D0%BD%D0%BE%D0%B9-%D1%89%D0%B5%D0%BD%D0%BE%D0%BA.jpg?s=612x612&w=0&k=20&c=JBlzq0XHEbvK6LVW9yYJRt7Jskl32dIUUQ-zeJXBnIM=",
        "https://media.istockphoto.com/id/615600530/ru/%D1%84%D0%BE%D1%82%D0%BE/%D0%B1%D0%BB%D0%BE%D0%BD%D0%B4%D0%B8%D0%BD%D0%BA%D0%B0-%D0%BB%D0%B0%D0%B1%D1%80%D0%B0%D0%B4%D0%BE%D1%80-%D1%80%D0%B5%D1%82%D1%80%D0%B8%D0%B2%D0%B5%D1%80-%D0%B3%D0%BB%D1%8F%D0%B4%D1%8F-%D0%BD%D0%B0-%D0%BA%D0%B0%D0%BC%D0%B5%D1%80%D1%83-%D0%BD%D0%BE%D0%BC%D0%B5%D1%80-%D0%B4%D0%BB%D1%8F-%D0%BA%D0%BE%D0%BF%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F.jpg?s=612x612&w=0&k=20&c=PAidFnYhOD1q2qVDtPlDb3sLZIYe9FOQQGaMzZaybQE=",
        "https://media.istockphoto.com/id/1136851201/ru/%D1%84%D0%BE%D1%82%D0%BE/%D0%B7%D0%BE%D0%BB%D0%BE%D1%82%D0%BE%D0%B9-%D0%BB%D0%B0%D0%B1%D1%80%D0%B0%D0%B4%D0%BE%D1%80-%D0%BB%D0%B0%D0%B1%D1%80%D0%B0%D0%B4%D0%BE%D1%80-%D1%80%D0%B5%D1%82%D1%80%D0%B8%D0%B2%D0%B5%D1%80-%D1%81%D0%BE%D0%B1%D0%B0%D0%BA%D0%B0-%D0%B6%D0%B5%D0%B2%D0%B0%D1%82%D0%B5%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F-%D0%BF%D0%BE%D1%82%D1%8F%D0%BD%D1%83%D0%B2-%D0%BD%D0%B0-%D0%BF%D0%BE%D0%B2%D0%BE%D0%B4%D0%BA%D0%B5-%D1%81%D0%B8%D0%BC%D0%BF%D0%B0%D1%82%D0%B8%D1%87%D0%BD%D1%8B%D0%B9-%D0%BE%D0%B7%D0%BE%D1%80%D0%BD%D0%BE%D0%B9.jpg?s=612x612&w=0&k=20&c=1P0aYXdzWfzOwiPvPwPUDaiGQXmmzL3utftuslEcxZk=",
        "https://media.istockphoto.com/id/1864218475/ru/%D1%84%D0%BE%D1%82%D0%BE/%D1%81%D0%BE%D0%B1%D0%B0%D0%BA%D0%B0-%D0%BF%D0%BE%D1%80%D0%BE%D0%B4%D1%8B-%D0%B7%D0%BE%D0%BB%D0%BE%D1%82%D0%B8%D1%81%D1%82%D1%8B%D0%B9-%D1%80%D0%B5%D1%82%D1%80%D0%B8%D0%B2%D0%B5%D1%80-%D0%BB%D0%B5%D0%B6%D0%B8%D1%82-%D0%B2-%D0%BF%D0%BE%D1%81%D1%82%D0%B5%D0%BB%D0%B8-%D1%83%D0%BA%D1%80%D1%8B%D1%82%D0%B0%D1%8F-%D1%82%D0%B5%D0%BF%D0%BB%D1%8B%D0%BC-%D0%B8-%D0%BC%D1%8F%D0%B3%D0%BA%D0%B8%D0%BC-%D0%BE%D0%B4%D0%B5%D1%8F%D0%BB%D0%BE%D0%BC-%D0%BF%D0%B8%D1%82%D0%BE%D0%BC%D0%B5%D1%86.jpg?s=612x612&w=0&k=20&c=WR81BLsHROnb400-Y8QpJh6-1y9ehW-_XtUK1WALqWo=",
        "https://media.istockphoto.com/id/545274718/ru/%D1%84%D0%BE%D1%82%D0%BE/%D1%81%D1%87%D0%B0%D1%81%D1%82%D0%BB%D0%B8%D0%B2%D0%B0%D1%8F-%D1%81%D0%BE%D0%B1%D0%B0%D0%BA%D0%B0.jpg?s=612x612&w=0&k=20&c=RHwcJuQgJGU3jMTM3wn6PnLC9oGF1Zu99RnzaSEpg9Y=",
        "https://media.istockphoto.com/id/1417321180/ru/%D1%84%D0%BE%D1%82%D0%BE/%D0%BC%D0%B8%D0%BB%D1%8B%D0%B9-%D1%89%D0%B5%D0%BD%D0%BE%D0%BA-%D0%BB%D0%B0%D0%B1%D1%80%D0%B0%D0%B4%D0%BE%D1%80%D0%B0-%D0%BD%D0%B0-%D1%81%D0%B2%D0%B5%D0%B6%D0%B5%D0%BC-%D0%B2%D0%BE%D0%B7%D0%B4%D1%83%D1%85%D0%B5-%D1%81%D0%BC%D0%BE%D1%82%D1%80%D0%B8%D1%82-%D0%B2-%D0%BA%D0%B0%D0%BC%D0%B5%D1%80%D1%83.jpg?s=612x612&w=0&k=20&c=Kvoahs3SdQunvWm4nsIiDZTl3-59LILFTGHtoGXtrsI=",
        "https://media.istockphoto.com/id/154918294/ru/%D1%84%D0%BE%D1%82%D0%BE/%D1%81%D1%87%D0%B0%D1%81%D1%82%D0%BB%D0%B8%D0%B2%D0%B0%D1%8F-%D1%81%D0%BE%D0%B1%D0%B0%D0%BA%D0%B0.jpg?s=612x612&w=0&k=20&c=98qATX4mM_UHay48f4hG1bFfqlP2z5ijJ4d6FdB0ieE=",
        "https://media.istockphoto.com/id/1408694637/ru/%D1%84%D0%BE%D1%82%D0%BE/%D0%B3%D0%BE%D0%BB%D0%BE%D0%B2%D0%B0-%D1%87%D0%B5%D1%80%D0%BD%D0%BE%D0%B3%D0%BE-%D0%BB%D0%B0%D0%B1%D1%80%D0%B0%D0%B4%D0%BE%D1%80%D0%B0-%D1%80%D0%B5%D1%82%D1%80%D0%B8%D0%B2%D0%B5%D1%80%D0%B0-%D0%B4%D1%80%D0%B5%D0%BC%D0%B0%D0%B5%D1%82-%D0%BD%D0%B0-%D0%BF%D0%BE%D0%BB%D1%83-%D0%B1%D0%BE%D0%BB%D0%B5%D0%B5-%D0%BF%D1%80%D0%BE%D1%85%D0%BB%D0%B0%D0%B4%D0%BD%D0%BE%D0%B3%D0%BE-%D0%B4%D0%BD%D1%8F.jpg?s=612x612&w=0&k=20&c=o4qiEBWvYe_H78Ra-34vF2skBhBG4B8LKB5idRNSKCk=",
        "https://media.istockphoto.com/id/2078759784/ru/%D1%84%D0%BE%D1%82%D0%BE/%D1%88%D0%BE%D0%BA%D0%BE%D0%BB%D0%B0%D0%B4%D0%BD%D1%8B%D0%B9-%D0%BB%D0%B0%D0%B1%D1%80%D0%B0%D0%B4%D0%BE%D1%80-%D1%80%D0%B5%D1%82%D1%80%D0%B8%D0%B2%D0%B5%D1%80-%D0%B2%D0%B2%D0%B5%D1%80%D1%85-%D0%BD%D0%BE%D0%B3%D0%B0%D0%BC%D0%B8-%D0%BA%D0%BB%D0%BE%D1%83%D0%BD%D0%B0%D0%B4%D0%B0.jpg?s=612x612&w=0&k=20&c=AV8QoHE8COMpGm0JFjmssDGMHbCGecYzybOLrc6GGGo="
    )

    val index = Random.nextInt(15)
    return avatars[index]
}
