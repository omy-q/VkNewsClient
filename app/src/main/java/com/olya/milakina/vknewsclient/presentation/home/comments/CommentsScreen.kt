package com.olya.milakina.vknewsclient.presentation.home.comments

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olya.milakina.vknewsclient.domain.Post
import com.olya.milakina.vknewsclient.ui.theme.DarkBlue

@Composable
fun CommentsScreen(
    post: Post,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Log.d("TEST", "CommentsScreen")

    val viewModel: CommentsScreenViewModel = viewModel(
        factory = CommentsScreenViewModelFactory(post),
        key = "postId = ${post.id}"
    )
    val state = viewModel.screenState.observeAsState(CommentsScreenState.Initial)
    when(val currentState = state.value) {
        is CommentsScreenState.Comments -> {
            CommentsUi(
                isNextPostsLoading = currentState.nextDataIsLoading,
                onScrollToBottom = viewModel::loadNextComments,
                comments = currentState.comments,
                onBackPressed = onBackPressed,
                modifier = modifier
            )
        }

        is CommentsScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = DarkBlue)
            }
        }

        is CommentsScreenState.Empty -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No comments now")
            }
        }

        is CommentsScreenState.Initial -> {}
    }
}
