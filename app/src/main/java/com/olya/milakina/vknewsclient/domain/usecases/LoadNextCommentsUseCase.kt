package com.olya.milakina.vknewsclient.domain.usecases

import com.olya.milakina.vknewsclient.domain.repositories.CommentsRepository

class LoadNextCommentsUseCase(
    private val repository: CommentsRepository
) {

    suspend operator fun invoke() {
        repository.loadNextComments()
    }
}
