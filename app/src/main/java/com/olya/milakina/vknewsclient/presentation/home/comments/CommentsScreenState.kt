package com.olya.milakina.vknewsclient.presentation.home.comments

import com.olya.milakina.vknewsclient.domain.entities.PostComment

internal sealed class CommentsScreenState {
    object Initial : CommentsScreenState()
    object Loading: CommentsScreenState()
    object Empty: CommentsScreenState()
    data class Comments(
        val comments: List<PostComment>,
        val nextDataIsLoading: Boolean = false
    ) : CommentsScreenState()

    object CommonError : CommentsScreenState()
}
