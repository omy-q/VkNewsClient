package com.olya.milakina.vknewsclient.presentation.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olya.milakina.vknewsclient.R
import com.olya.milakina.vknewsclient.domain.entities.Post
import com.olya.milakina.vknewsclient.ui.theme.DarkBlue

@Composable
fun HomeScreen(
    onCommentClickListener: (post: Post) -> Unit,
    modifier: Modifier = Modifier
) {
    Log.d("TEST", "HomeScreen")

    val viewModel: HomeScreenViewModel = viewModel()
    val state = viewModel.screenState.collectAsState(HomeScreenState.Initial)

    when (val currentState = state.value) {
        is HomeScreenState.Posts -> {
            HomeUi(
                posts = currentState.posts,
                isNextPostsLoading = currentState.nextDataIsLoading,
                onScrollToBottom = viewModel::loadNextPosts,
                onDeletePostListener = viewModel::deletePost,
                onCommentClickListener = onCommentClickListener,
                onLikeClickListener = viewModel::changeLikeStatus,
                modifier = modifier
            )

            if (currentState.showToastError) {
                Toast.makeText(
                    LocalContext.current,
                    stringResource(R.string.common_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        is HomeScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = DarkBlue)
            }
        }

        is HomeScreenState.CommonError -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.common_error),
                    textAlign = TextAlign.Center
                )
            }
        }

        is HomeScreenState.Initial -> {}
    }
}
