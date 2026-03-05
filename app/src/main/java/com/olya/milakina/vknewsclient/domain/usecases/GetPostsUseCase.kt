package com.olya.milakina.vknewsclient.domain.usecases

import com.olya.milakina.vknewsclient.domain.entities.PaginationState
import com.olya.milakina.vknewsclient.domain.entities.Post
import com.olya.milakina.vknewsclient.domain.repositories.PostRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

internal class GetPostsUseCase @Inject constructor(
    private val repository: PostRepository
) {

    operator fun invoke(): StateFlow<PaginationState<Post>> {
        return repository.loadPosts()
    }
}
