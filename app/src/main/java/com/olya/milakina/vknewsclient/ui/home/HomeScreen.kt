package com.olya.milakina.vknewsclient.ui.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olya.milakina.vknewsclient.domain.Post

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
                onDeletePostListener = viewModel::deletePost,
                onViewsClickListener = viewModel::updateStatisticCount,
                onShareClickListener = viewModel::updateStatisticCount,
                onCommentClickListener = onCommentClickListener,
                onLikeClickListener = viewModel::updateStatisticCount,
                modifier = modifier
            )
        }

        is HomeScreenState.Initial -> {}
    }
}
