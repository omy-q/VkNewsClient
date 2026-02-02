package com.example.vknewsclient.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewsclient.domain.StatisticItem
import com.example.vknewsclient.domain.VkPost

class HomeScreenViewModel : ViewModel() {
    private val _screenState = MutableLiveData<HomeScreenState>(HomeScreenState.Initial)
    val screenState: LiveData<HomeScreenState> = _screenState

    init {
        loadPosts()
    }

    private fun loadPosts() {
        val posts = mutableListOf<VkPost>().apply {
            repeat(50) {
                add(
                    VkPost(
                        id = it,
                        title = "Test Title $it"
                    )
                )
            }
        }
        _screenState.value = HomeScreenState.Posts(posts)
    }

    fun updateStatisticCount(postId: Int, statisticItem: StatisticItem) {
        val currentState = _screenState.value
        if (currentState !is HomeScreenState.Posts) return
        val modifiedPosts = currentState.vkPosts.toMutableList()
        modifiedPosts.replaceAll {
            if (it.id == postId) {
                val newStatistics = it.statistics.toMutableList().apply {
                    replaceAll { oldItem ->
                        if (oldItem.type == statisticItem.type) {
                            oldItem.copy(count = oldItem.count + 1)
                        } else oldItem
                    }
                }
                it.copy(statistics = newStatistics)
            } else it
        }
        _screenState.value = HomeScreenState.Posts(modifiedPosts)
    }

    fun deletePost(postId: Int) {
        val currentState = _screenState.value
        if (currentState !is HomeScreenState.Posts) return
        val modifiedPosts = currentState.vkPosts.toMutableList()
        modifiedPosts.removeIf { it.id == postId }
        _screenState.value = HomeScreenState.Posts(modifiedPosts)
    }
}
