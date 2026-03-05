package com.olya.milakina.vknewsclient.presentation.home.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olya.milakina.vknewsclient.PaginationState
import com.olya.milakina.vknewsclient.data.comments.CommentsRepository
import com.olya.milakina.vknewsclient.data.comments.CommentsRepositoryImpl
import com.olya.milakina.vknewsclient.domain.Post
import com.olya.milakina.vknewsclient.domain.PostComment
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CommentsScreenViewModel(val post: Post) : ViewModel() {

    private val repository: CommentsRepository = CommentsRepositoryImpl()
    private var comments: List<PostComment> = listOf()
    val screenState = repository.loadComments(post)
        .map {
            when (it) {
                is PaginationState.FirstPageLoading -> {
                    CommentsScreenState.Loading
                }

                is PaginationState.NextPageLoading -> {
                    CommentsScreenState.Comments(comments, nextDataIsLoading = true)
                }

                is PaginationState.PageLoaded -> {
                    comments = it.data
                    if (it.data.isEmpty()) {
                        CommentsScreenState.Empty
                    } else {
                        CommentsScreenState.Comments(it.data, nextDataIsLoading = false)
                    }
                }

                is PaginationState.AllPagesLoaded -> {
                    comments = it.data
                    if (it.data.isEmpty()) {
                        CommentsScreenState.Empty
                    } else {
                        CommentsScreenState.Comments(it.data, nextDataIsLoading = false)
                    }
                }

                is PaginationState.FailureLoading -> {
                    CommentsScreenState.CommonError
                }
            }
        }.onStart { emit(CommentsScreenState.Loading) }


    fun loadNextComments() {
        viewModelScope.launch {
            repository.loadNextComments()
        }
    }
}
