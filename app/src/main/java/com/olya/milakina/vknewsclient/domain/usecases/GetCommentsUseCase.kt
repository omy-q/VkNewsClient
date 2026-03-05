package com.olya.milakina.vknewsclient.domain.usecases

import com.olya.milakina.vknewsclient.domain.entities.PaginationState
import com.olya.milakina.vknewsclient.domain.entities.Post
import com.olya.milakina.vknewsclient.domain.entities.PostComment
import com.olya.milakina.vknewsclient.domain.repositories.CommentsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetCommentsUseCase @Inject constructor(
    private val repository: CommentsRepository
) {

    operator fun invoke(post: Post): Flow<PaginationState<PostComment>> {
        return repository.loadComments(post)
    }
}
