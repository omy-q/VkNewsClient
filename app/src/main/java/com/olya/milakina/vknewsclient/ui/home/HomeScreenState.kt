package com.olya.milakina.vknewsclient.ui.home

import com.olya.milakina.vknewsclient.domain.Post

sealed class HomeScreenState {
    object Initial: HomeScreenState()
    data class Posts(val vkPosts: List<Post>) : HomeScreenState()
}
