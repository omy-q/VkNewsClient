package com.example.vknewsclient.ui.elements

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

@Composable
internal fun CardPanel(
    seeCount: Int,
    shareCount: Int,
    commentCount: Int,
    likeCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        IconWithText(
            iconResId = R.drawable.ic_views,
            text = seeCount.toString()
        )

        Spacer(modifier = Modifier.weight(1f))

        IconWithText(
            iconResId = R.drawable.ic_share,
            text = shareCount.toString()
        )

        IconWithText(
            iconResId = R.drawable.ic_comment,
            text = commentCount.toString()
        )

        IconWithText(
            iconResId = R.drawable.ic_like,
            text = likeCount.toString()
        )
    }
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    modifier: Modifier = Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
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
