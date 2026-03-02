package com.olya.milakina.vknewsclient.presentation.home

import com.olya.milakina.vknewsclient.domain.Post

sealed class HomeScreenState {
    object Initial: HomeScreenState()
    object Loading: HomeScreenState()
    data class Posts(val vkPosts: List<Post>, val nextDataIsLoading: Boolean = false) : HomeScreenState()
}
