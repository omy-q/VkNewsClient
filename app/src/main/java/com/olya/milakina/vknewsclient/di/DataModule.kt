package com.olya.milakina.vknewsclient.di

import android.content.Context
import com.olya.milakina.vknewsclient.data.ApiFactory
import com.olya.milakina.vknewsclient.data.ApiService
import com.olya.milakina.vknewsclient.data.SecurePrefs
import com.olya.milakina.vknewsclient.data.auth.AuthRepositoryImpl
import com.olya.milakina.vknewsclient.data.posts.PostsRepositoryImpl
import com.olya.milakina.vknewsclient.domain.repositories.AuthRepository
import com.olya.milakina.vknewsclient.domain.repositories.PostRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface DataModule {

    @ApplicationScope
    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @ApplicationScope
    @Binds
    fun bindPostsRepository(impl: PostsRepositoryImpl): PostRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService{
            return ApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun  provideSecurePrefs(context: Context): SecurePrefs {
            return SecurePrefs(context)
        }
    }
}
