package com.example.vknewsclient.ui.home.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CommentsScreenViewModelFactory(private val postId: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentsScreenViewModel::class.java))
            return CommentsScreenViewModel(postId) as T
        throw IllegalStateException("Unknown ViewModel class: ${modelClass.name}")
    }
}
