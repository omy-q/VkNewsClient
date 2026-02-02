package com.example.vknewsclient.ui.home.comments

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CommentsScreen(
    postId: Int,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Log.d("TEST", "CommentsScreen")

    val viewModel: CommentsScreenViewModel = viewModel(
        factory = CommentsScreenViewModelFactory(postId),
        key = "postId = $postId"
    )
    val state = viewModel.screenState.observeAsState(CommentsScreenState.Initial)
    when(val currentState = state.value) {
        is CommentsScreenState.Comments -> {
            CommentsUi(
                postId = currentState.postId,
                comments = currentState.comments,
                onBackPressed = onBackPressed,
                modifier = modifier
            )
        }

        is CommentsScreenState.Initial -> {}
    }
}
