package com.olya.milakina.vknewsclient.presentation.home

import com.olya.milakina.vknewsclient.domain.entities.Post

internal sealed class HomeScreenState {
    object Initial : HomeScreenState()
    object Loading : HomeScreenState()
    data class Posts(
        val posts: List<Post>,
        val nextDataIsLoading: Boolean = false,
        val showToastError: Boolean = false
    ) : HomeScreenState()

    object CommonError : HomeScreenState()
}
