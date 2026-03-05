package com.olya.milakina.vknewsclient.di

import com.olya.milakina.vknewsclient.data.comments.CommentsRepositoryImpl
import com.olya.milakina.vknewsclient.domain.repositories.CommentsRepository
import dagger.Binds
import dagger.Module

@Module
internal interface CommentsScreenDataModule {

    @CommentsScreenScope
    @Binds
    fun bindCommentsRepository(impl: CommentsRepositoryImpl): CommentsRepository
}
