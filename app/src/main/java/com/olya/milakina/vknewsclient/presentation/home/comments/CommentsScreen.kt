package com.olya.milakina.vknewsclient.presentation.home.comments

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olya.milakina.vknewsclient.R
import com.olya.milakina.vknewsclient.domain.entities.Post
import com.olya.milakina.vknewsclient.presentation.getApplicationComponent
import com.olya.milakina.vknewsclient.ui.theme.DarkBlue

@Composable
internal fun CommentsScreen(
    post: Post,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Log.d("TEST", "CommentsScreen")

    val component = getApplicationComponent().getCommentsScreenComponentFactory().create(post)
    val viewModel: CommentsScreenViewModel = viewModel(factory = component.getCommentsViewModelFactory())
    val state = viewModel.screenState.collectAsState(CommentsScreenState.Initial)

    CommentsScreenContent(
        screenState = state,
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        modifier = modifier
    )
}

@Composable
private fun CommentsScreenContent(
    screenState: State<CommentsScreenState>,
    viewModel: CommentsScreenViewModel,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier

) {
    Log.d("TEST", "CommentsScreenContent")

    when (val currentState = screenState.value) {
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
                Text(
                    text = stringResource(R.string.comments_empty_state),
                    textAlign = TextAlign.Center
                )
            }
        }

        is CommentsScreenState.CommonError -> {
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

        is CommentsScreenState.Initial -> {}
    }
}
