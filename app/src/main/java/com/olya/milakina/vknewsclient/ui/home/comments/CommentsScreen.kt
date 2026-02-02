package com.olya.milakina.vknewsclient.ui.home.comments

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olya.milakina.vknewsclient.domain.Post

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
                postId = currentState.post.id,
                comments = currentState.comments,
                onBackPressed = onBackPressed,
                modifier = modifier
            )
        }

        is CommentsScreenState.Initial -> {}
    }
}
