package com.olya.milakina.vknewsclient.domain.usecases

import com.olya.milakina.vknewsclient.domain.repositories.PostRepository

class LoadNextPostsUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke() {
        repository.loadNextPosts()
    }
}
