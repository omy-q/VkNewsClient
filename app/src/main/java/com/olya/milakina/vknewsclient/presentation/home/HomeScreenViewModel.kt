package com.olya.milakina.vknewsclient.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.olya.milakina.vknewsclient.data.posts.PostRepository
import com.olya.milakina.vknewsclient.data.posts.PostsRepositoryImpl
import com.olya.milakina.vknewsclient.domain.Post
import kotlinx.coroutines.launch

class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PostRepository = PostsRepositoryImpl(application)
    private val _screenState = MutableLiveData<HomeScreenState>(HomeScreenState.Initial)
    val screenState: LiveData<HomeScreenState> = _screenState

    private var isChangeLikeLoading: Boolean = false

    init {
        _screenState.value = HomeScreenState.Loading
        loadPosts()
    }

    fun loadNextPosts() {
        if (repository.hasNext) {
            _screenState.value = HomeScreenState.Posts(repository.posts, true)
            viewModelScope.launch {
                loadPosts()
            }
        }
    }

    private fun loadPosts() {
        viewModelScope.launch {
            repository.loadPosts()
            _screenState.value = HomeScreenState.Posts(repository.posts)
        }
    }

    fun changeLikeStatus(post: Post) {
        if (!isChangeLikeLoading) {
            isChangeLikeLoading = true
            viewModelScope.launch {
                repository.changeLikeStatus(post)
                _screenState.value = HomeScreenState.Posts(repository.posts)
                isChangeLikeLoading = false
            }
        }
    }

    fun deletePost(post: Post) {
        val currentState = _screenState.value
        if (currentState !is HomeScreenState.Posts) return
        viewModelScope.launch {
            repository.deletePost(post)
            _screenState.value = HomeScreenState.Posts(repository.posts)
        }
    }
}
