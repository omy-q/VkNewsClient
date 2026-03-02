package com.example.vknewsclient.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vknewsclient.domain.StatisticItem
import com.example.vknewsclient.domain.VkPost
import com.example.vknewsclient.ui.elements.NewsCard

@Composable
fun HomeUi(
    posts: List<VkPost>,
    onDeletePostListener: (id: Int) -> Unit,
    onViewsClickListener: (postId: Int, statistics: StatisticItem) -> Unit,
    onShareClickListener: (postId: Int, statistics: StatisticItem) -> Unit,
    onCommentClickListener: (postId: Int) -> Unit,
    onLikeClickListener: (postId: Int, statistics: StatisticItem) -> Unit,
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
                onDismiss = { onDeletePostListener.invoke(post.id) },
                backgroundContent = {}
            ) {
                NewsCard(
                    news = post,
                    onViewsClickListener = { onViewsClickListener.invoke(post.id, it) },
                    onShareClickListener = { onShareClickListener.invoke(post.id, it) },
                    onCommentClickListener = { onCommentClickListener.invoke(post.id) },
                    onLikeClickListener = { onLikeClickListener.invoke(post.id, it) }
                )
            }
        }
    }
}
