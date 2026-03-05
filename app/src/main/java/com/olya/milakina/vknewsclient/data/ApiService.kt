package com.olya.milakina.vknewsclient.data

import com.olya.milakina.vknewsclient.data.comments.model.CommentsDto
import com.olya.milakina.vknewsclient.data.posts.model.PostsDto
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ApiService {
    @GET("top-headlines")
    suspend fun getPosts(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("category") category: String = "general",
    ): PostsDto

    @GET("everything")
    suspend fun getComments(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("q") query: String
    ): CommentsDto
}
