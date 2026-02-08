package com.olya.milakina.vknewsclient.data

import com.olya.milakina.vknewsclient.data.model.PostsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getPosts(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("country") country: String = "us",
    ): PostsDto

}
