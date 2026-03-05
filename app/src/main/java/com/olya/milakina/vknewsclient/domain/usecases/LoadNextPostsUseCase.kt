package com.olya.milakina.vknewsclient.domain.usecases

import com.olya.milakina.vknewsclient.domain.repositories.PostRepository
import javax.inject.Inject

internal class LoadNextPostsUseCase @Inject constructor(
    private val repository: PostRepository
) {

    suspend operator fun invoke() {
        repository.loadNextPosts()
    }
}
