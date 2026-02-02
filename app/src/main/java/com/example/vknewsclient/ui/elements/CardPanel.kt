package com.example.vknewsclient.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vknewsclient.R
import com.example.vknewsclient.domain.StatisticItem
import com.example.vknewsclient.domain.StatisticType

@Composable
internal fun CardPanel(
    statistics: List<StatisticItem>,
    onViewsClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
    onLikeClickListener: (StatisticItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val viewsItem = statistics.getItemByType(StatisticType.Views)
        IconWithText(
            iconResId = R.drawable.ic_views,
            text = viewsItem.count.toString(),
            onItemClick = { onViewsClickListener.invoke(viewsItem) }
        )

        Spacer(modifier = Modifier.weight(1f))

        val sharesItem = statistics.getItemByType(StatisticType.Shares)
        IconWithText(
            iconResId = R.drawable.ic_share,
            text = sharesItem.count.toString(),
            onItemClick = { onShareClickListener.invoke(sharesItem) }
        )

        val commentsItem = statistics.getItemByType(StatisticType.Comments)
        IconWithText(
            iconResId = R.drawable.ic_comment,
            text = commentsItem.count.toString(),
            onItemClick = { onCommentClickListener.invoke(commentsItem) }
        )

        val likesItem = statistics.getItemByType(StatisticType.Likes)
        IconWithText(
            iconResId = R.drawable.ic_like,
            text = likesItem.count.toString(),
            onItemClick = { onLikeClickListener.invoke(likesItem) }
        )
    }
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.clickable {
            onItemClick.invoke()
        }
    ) {
        Icon(
            painter = painterResource(iconResId),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
        )
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find { it.type == type } ?: throw IllegalStateException()
}
