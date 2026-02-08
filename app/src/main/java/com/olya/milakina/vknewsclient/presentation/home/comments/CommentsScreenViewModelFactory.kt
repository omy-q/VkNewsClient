package com.olya.milakina.vknewsclient.presentation.home.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olya.milakina.vknewsclient.domain.Post

class CommentsScreenViewModelFactory(private val post: Post) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentsScreenViewModel::class.java))
            return CommentsScreenViewModel(post) as T
        throw IllegalStateException("Unknown ViewModel class: ${modelClass.name}")
    }
}
