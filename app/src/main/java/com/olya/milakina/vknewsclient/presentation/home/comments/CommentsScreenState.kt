package com.olya.milakina.vknewsclient.presentation.home.comments

import com.olya.milakina.vknewsclient.domain.PostComment
import com.olya.milakina.vknewsclient.domain.Post

sealed class CommentsScreenState {
    object Initial: CommentsScreenState()
    data class Comments(val post: Post, val comments: List<PostComment>) : CommentsScreenState()
}
