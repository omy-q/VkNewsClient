package com.example.vknewsclient.ui.home.comments

import com.example.vknewsclient.domain.PostComment

sealed class CommentsScreenState {
    object Initial: CommentsScreenState()
    data class Comments(val postId: Int, val comments: List<PostComment>) : CommentsScreenState()
}

