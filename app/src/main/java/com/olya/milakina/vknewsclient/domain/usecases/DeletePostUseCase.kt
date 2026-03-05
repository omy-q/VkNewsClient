package com.olya.milakina.vknewsclient.domain.usecases

import com.olya.milakina.vknewsclient.domain.entities.Post
import com.olya.milakina.vknewsclient.domain.repositories.PostRepository
import javax.inject.Inject

internal class DeletePostUseCase @Inject constructor(
    private val repository: PostRepository
) {

    suspend operator fun invoke(post: Post) {
        repository.deletePost(post)
    }
}
