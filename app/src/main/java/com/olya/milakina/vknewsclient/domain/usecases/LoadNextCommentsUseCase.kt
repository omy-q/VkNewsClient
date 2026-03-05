package com.olya.milakina.vknewsclient.domain.usecases

import com.olya.milakina.vknewsclient.domain.repositories.CommentsRepository
import javax.inject.Inject

internal class LoadNextCommentsUseCase @Inject constructor(
    private val repository: CommentsRepository
) {

    suspend operator fun invoke() {
        repository.loadNextComments()
    }
}
