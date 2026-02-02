package com.example.vknewsclient.ui.home.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewsclient.domain.PostComment

class CommentsScreenViewModel(postId: Int) : ViewModel() {
    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(postId)
    }

    private fun loadComments(postId: Int) {
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

        _screenState.value = CommentsScreenState.Comments(postId, comments)
    }


}
