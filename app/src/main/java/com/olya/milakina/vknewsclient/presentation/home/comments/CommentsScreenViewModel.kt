package com.olya.milakina.vknewsclient.presentation.home.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olya.milakina.vknewsclient.domain.PostComment
import com.olya.milakina.vknewsclient.domain.Post

class CommentsScreenViewModel(post: Post) : ViewModel() {
    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(post)
    }

    private fun loadComments(post: Post) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(
                    PostComment(
                        id = it,
                        authorName = "Author CommentId = $it"
                    )
                )
            }
        }

        _screenState.value = CommentsScreenState.Comments(post, comments)
    }
}
