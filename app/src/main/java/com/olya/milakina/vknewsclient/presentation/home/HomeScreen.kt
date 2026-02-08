package com.olya.milakina.vknewsclient.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olya.milakina.vknewsclient.domain.Post
import com.olya.milakina.vknewsclient.ui.theme.DarkBlue

@Composable
fun HomeScreen(
    onCommentClickListener: (post: Post) -> Unit,
    modifier: Modifier = Modifier
) {
    Log.d("TEST", "HomeScreen")

    val viewModel: HomeScreenViewModel = viewModel()
    val state = viewModel.screenState.observeAsState(HomeScreenState.Initial)

    when (val currentState = state.value) {
        is HomeScreenState.Posts -> {
            HomeUi(
                posts = currentState.vkPosts,
                isNextPostsLoading = currentState.nextDataIsLoading,
                onScrollToBottom = viewModel::loadNextPosts,
                onDeletePostListener = viewModel::deletePost,
                onCommentClickListener = onCommentClickListener,
                onLikeClickListener = viewModel::changeLikeStatus,
                modifier = modifier
            )
        }

        is HomeScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = DarkBlue)
            }
        }

        is HomeScreenState.Initial -> {}
    }
}
