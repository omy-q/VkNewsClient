package com.olya.milakina.vknewsclient.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.olya.milakina.vknewsclient.R
import com.olya.milakina.vknewsclient.domain.StatisticItem
import com.olya.milakina.vknewsclient.domain.StatisticType
import com.olya.milakina.vknewsclient.ui.theme.DarkRed
import java.util.Locale

@Composable
internal fun CardPanel(
    statistics: List<StatisticItem>,
    isLiked: Boolean,
    onCommentClickListener: () -> Unit,
    onLikeClickListener: (StatisticItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val viewsItem = statistics.getItemByType(StatisticType.Views)
        IconWithText(
            iconResId = R.drawable.ic_views,
            text = viewsItem.count.formatStatisticCount(),
        )

        Spacer(modifier = Modifier.weight(1f))

        val sharesItem = statistics.getItemByType(StatisticType.Shares)
        IconWithText(
            iconResId = R.drawable.ic_share,
            text = sharesItem.count.formatStatisticCount(),
        )

        val commentsItem = statistics.getItemByType(StatisticType.Comments)
        IconWithText(
            iconResId = R.drawable.ic_comment,
            text = commentsItem.count.formatStatisticCount(),
            onItemClick = onCommentClickListener
        )

        val likesItem = statistics.getItemByType(StatisticType.Likes)
        IconWithText(
            iconResId = if (isLiked) R.drawable.ic_liked else R.drawable.ic_like,
            iconTint = if (isLiked) DarkRed else MaterialTheme.colorScheme.onSecondary,
            text = likesItem.count.formatStatisticCount(),
            onItemClick = { onLikeClickListener.invoke(likesItem) }
        )
    }
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    modifier: Modifier = Modifier,
    onItemClick: (() -> Unit)? = null,
    iconTint: Color = MaterialTheme.colorScheme.onSecondary
) {
    val rowModifier = if (onItemClick == null) Modifier else {
        Modifier.clickable {
            onItemClick.invoke()
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .then(rowModifier)
            .padding(4.dp),
    ) {
        Icon(
            painter = painterResource(iconResId),
            contentDescription = "",
            tint = iconTint,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = 12.sp,
            fontFamily = FontFamily.Monospace,
        )
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find { it.type == type } ?: throw IllegalStateException()
}

private fun Int.formatStatisticCount(): String {
    return when {
        this > 100_000 -> String.format(Locale.getDefault(), "%K", (this / 1000))
        this > 1000 -> String.format(Locale.getDefault(), "%.1fK", (this / 1000f))
        else -> this.toString()
    }

}
