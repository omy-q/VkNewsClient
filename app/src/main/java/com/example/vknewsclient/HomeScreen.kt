package com.example.vknewsclient

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vknewsclient.ui.elements.NewsCard

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val posts = viewModel.vkPosts.observeAsState(listOf())
    Log.d("TEST", "HomeScreen")
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
        items(posts.value, key = { it.id }) { post ->
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
                onDismiss = { viewModel.deletePost(post.id) },
                backgroundContent = {}
            ) {
                NewsCard(
                    news = post,
                    onViewsClickListener = { viewModel.updateStatisticCount(post.id, it) },
                    onShareClickListener = { viewModel.updateStatisticCount(post.id, it) },
                    onCommentClickListener = { viewModel.updateStatisticCount(post.id, it) },
                    onLikeClickListener = { viewModel.updateStatisticCount(post.id, it) }
                )
            }
        }
    }
}
