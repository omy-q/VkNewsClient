package com.example.vknewsclient

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vknewsclient.ui.elements.NewsCard

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Log.d("TEST", "MainScreen")
    Scaffold(
        bottomBar = { VkNewsNavigationBar() },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        val posts = viewModel.vkPosts.observeAsState(listOf())
        Log.d("TEST", "Scaffold")

        LazyColumn(
            modifier = Modifier.padding(innerPadding),
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
}

@Composable
private fun VkNewsNavigationBar(
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = modifier
    ) {
        val selectedItemPosition = remember { mutableIntStateOf(0) }
        val items = listOf(
            NavigationItem.Home,
            NavigationItem.Favorite,
            NavigationItem.Profile
        )

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemPosition.value == index,
                onClick = {
                    selectedItemPosition.value = index
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                },
                label = { Text(text = stringResource(item.titleResId)) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSecondary
                )
            )
        }
    }
}
