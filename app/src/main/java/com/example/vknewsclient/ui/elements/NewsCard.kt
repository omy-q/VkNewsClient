package com.example.vknewsclient.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.vknewsclient.R
import com.example.vknewsclient.ui.theme.VkNewsClientTheme

@Composable
fun NewsCard(
    titleIcon: String?,
    title: String,
    time: String,
    description: String,
    cardImage: String?,
    seeCount: Int,
    shareCount: Int,
    commentCount: Int,
    likeCount: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(8.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            CardTitle(
                icon = titleIcon,
                title = title,
                time = time
            )

            Text(
                text = description,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 12.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            AsyncImage(
                model = cardImage,
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )

            CardPanel(
                seeCount = seeCount,
                shareCount = shareCount,
                commentCount = commentCount,
                likeCount = likeCount
            )
        }
    }
}

@Preview
@Composable
private fun NewCardPreviewLight() {
    VkNewsClientTheme(darkTheme = false) {
        NewsCard(
            titleIcon = null,
            title = "уволено",
            time = "14:00",
            description = "hjb j,yf,j cghvjbk chgfjk yfjhgio kdfguhkjl cgtfhyji nhgfygjuhijo fcghjuji cghfjk",
            cardImage = null,
            seeCount = 206,
            shareCount = 206,
            commentCount = 11,
            likeCount = 491
        )
    }
}

@Preview
@Composable
private fun NewCardPreviewDark() {
    VkNewsClientTheme(darkTheme = true) {
        NewsCard(
            titleIcon = null,
            title = "уволено",
            time = "14:00",
            description = "hjb j,yf,j cghvjbk chgfjk yfjhgio kdfguhkjl cgtfhyji nhgfygjuhijo fcghjuji cghfjk",
            cardImage = null,
            seeCount = 206,
            shareCount = 206,
            commentCount = 11,
            likeCount = 491
        )
    }
}
