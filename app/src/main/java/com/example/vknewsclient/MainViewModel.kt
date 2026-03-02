package com.example.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewsclient.domain.StatisticItem
import com.example.vknewsclient.domain.VkPost

class MainViewModel : ViewModel() {

    private val initialVkPosts = mutableListOf<VkPost>().apply {
        repeat(50) {
            add(
                VkPost(
                    id = it,
                    title = "Test Title $it"
                )
            )
        }
    }

    private val _vkPosts = MutableLiveData<List<VkPost>>(initialVkPosts)
    val vkPosts: LiveData<List<VkPost>> = _vkPosts

    fun updateStatisticCount(postId: Int, statisticItem: StatisticItem) {
        val modifiedPosts = _vkPosts.value?.toMutableList() ?: mutableListOf()
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
        _vkPosts.value = modifiedPosts
    }

    fun deletePost(postId: Int) {
        val modifiedPosts = _vkPosts.value?.toMutableList() ?: mutableListOf()
        modifiedPosts.removeIf { it.id == postId }
        _vkPosts.value = modifiedPosts
    }
}