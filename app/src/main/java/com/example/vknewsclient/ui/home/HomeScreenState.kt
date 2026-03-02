package com.example.vknewsclient.ui.home

import com.example.vknewsclient.domain.VkPost

sealed class HomeScreenState {
    object Initial: HomeScreenState()
    data class Posts(val vkPosts: List<VkPost>) : HomeScreenState()
}
