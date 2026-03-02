package com.olya.milakina.vknewsclient.data.comments

import com.olya.milakina.vknewsclient.domain.Post
import com.olya.milakina.vknewsclient.domain.PostComment

interface CommentsRepository {

    val comments: List<PostComment>
    val hasNext: Boolean

    suspend fun loadComments(post: Post)
}