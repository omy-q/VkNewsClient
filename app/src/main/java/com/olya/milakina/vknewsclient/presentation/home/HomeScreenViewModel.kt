package com.olya.milakina.vknewsclient.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olya.milakina.vknewsclient.domain.entities.PaginationState
import com.olya.milakina.vknewsclient.domain.entities.Post
import com.olya.milakina.vknewsclient.domain.usecases.ChangeLikeStatusUseCase
import com.olya.milakina.vknewsclient.domain.usecases.DeletePostUseCase
import com.olya.milakina.vknewsclient.domain.usecases.GetPostsUseCase
import com.olya.milakina.vknewsclient.domain.usecases.LoadNextPostsUseCase
import com.olya.milakina.vknewsclient.mergeWith
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class HomeScreenViewModel @Inject constructor(
    getPostsUseCase: GetPostsUseCase,
    private val loadNextPostsUseCase: LoadNextPostsUseCase,
    private val changeLikeStatusUseCase: ChangeLikeStatusUseCase,
    private val deletePostUseCase: DeletePostUseCase
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        isChangeLikeLoading = false
        showToastErrorEvent.tryEmit(Unit)
    }

    private val showToastErrorEvent = MutableSharedFlow<Unit>(replay = 1)
    private val showToastErrorFlow = flow {
        showToastErrorEvent.collect {
            emit(
                HomeScreenState.Posts(posts, showToastError = true)
            )
            delay(200)
            emit(HomeScreenState.Posts(posts))
        }
    }
    private var isChangeLikeLoading: Boolean = false
    private var posts: List<Post> = listOf()

    val screenState = getPostsUseCase()
        .map {
            when (it) {
                is PaginationState.FirstPageLoading -> {
                    HomeScreenState.Loading
                }

                is PaginationState.NextPageLoading -> {
                    HomeScreenState.Posts(posts, nextDataIsLoading = true)
                }

                is PaginationState.PageLoaded -> {
                    posts = it.data
                    HomeScreenState.Posts(it.data, nextDataIsLoading = false)
                }

                is PaginationState.AllPagesLoaded -> {
                    posts = it.data
                    HomeScreenState.Posts(it.data, nextDataIsLoading = false)
                }

                is PaginationState.FailureLoading -> {
                    HomeScreenState.CommonError
                }
            }
        }
        .onStart { emit(HomeScreenState.Loading) }
        .mergeWith(showToastErrorFlow)

    fun loadNextPosts() {
        viewModelScope.launch {
            loadNextPostsUseCase()
        }
    }

    fun changeLikeStatus(post: Post) {
        if (!isChangeLikeLoading) {
            isChangeLikeLoading = true
            viewModelScope.launch(exceptionHandler) {
                changeLikeStatusUseCase(post)
                isChangeLikeLoading = false
            }
        }
    }

    fun deletePost(post: Post) {
        viewModelScope.launch(exceptionHandler) {
            deletePostUseCase(post)
        }
    }
}
