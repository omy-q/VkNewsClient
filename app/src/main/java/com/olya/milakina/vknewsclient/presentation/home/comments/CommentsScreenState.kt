package com.olya.milakina.vknewsclient.presentation.home.comments

import com.olya.milakina.vknewsclient.domain.PostComment
import com.olya.milakina.vknewsclient.domain.Post

sealed class CommentsScreenState {
    object Initial : CommentsScreenState()
    object Loading: CommentsScreenState()
    object Empty: CommentsScreenState()
    data class Comments(
        val comments: List<PostComment>,
        val nextDataIsLoading: Boolean = false
    ) : CommentsScreenState()
}
