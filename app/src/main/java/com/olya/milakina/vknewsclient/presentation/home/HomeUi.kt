package com.olya.milakina.vknewsclient.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.olya.milakina.vknewsclient.domain.Post
import com.olya.milakina.vknewsclient.ui.elements.NewsCard
import com.olya.milakina.vknewsclient.ui.theme.DarkBlue

@Composable
fun HomeUi(
    posts: List<Post>,
    isNextPostsLoading: Boolean,
    onScrollToBottom: () -> Unit,
    onDeletePostListener: (post: Post) -> Unit,
    onCommentClickListener: (post: Post) -> Unit,
    onLikeClickListener: (post: Post) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(posts, key = { it.id }) { post ->
            val dismissState = rememberSwipeToDismissBoxState(
                positionalThreshold = { it * 0.5f },
                confirmValueChange = {
                    it in setOf(SwipeToDismissBoxValue.EndToStart)
                }
            )

            SwipeToDismissBox(
                modifier = Modifier.animateItem(),
                state = dismissState,
                enableDismissFromEndToStart = true,
                enableDismissFromStartToEnd = false,
                onDismiss = { onDeletePostListener.invoke(post) },
                backgroundContent = {}
            ) {
                NewsCard(
                    news = post,
                    onCommentClickListener = { onCommentClickListener.invoke(post) },
                    onLikeClickListener = { onLikeClickListener.invoke(post) }
                )
            }
        }

        item {
            if (isNextPostsLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = DarkBlue)
                }
            } else {
                SideEffect {
                    onScrollToBottom.invoke()
                }
            }
        }
    }
}
