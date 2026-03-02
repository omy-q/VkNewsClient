package com.olya.milakina.vknewsclient.presentation.home.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olya.milakina.vknewsclient.data.comments.CommentsRepository
import com.olya.milakina.vknewsclient.data.comments.CommentsRepositoryImpl
import com.olya.milakina.vknewsclient.domain.Post
import kotlinx.coroutines.launch

class CommentsScreenViewModel(val post: Post) : ViewModel() {

    private val repository: CommentsRepository = CommentsRepositoryImpl()
    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments()
    }

    fun loadNextComments() {
        if (repository.hasNext) {
            _screenState.value = CommentsScreenState.Comments(repository.comments, true)
            viewModelScope.launch {
                loadComments()
            }
        }
    }

    private fun loadComments() {
        viewModelScope.launch {
            repository.loadComments(post)
            val comments = repository.comments
            if (comments.isEmpty()) {
                _screenState.value = CommentsScreenState.Empty
            } else {
                _screenState.value = CommentsScreenState.Comments(repository.comments)
            }
        }
    }
}
