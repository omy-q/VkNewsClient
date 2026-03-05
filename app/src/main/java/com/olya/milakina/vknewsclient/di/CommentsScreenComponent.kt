package com.olya.milakina.vknewsclient.di

import com.olya.milakina.vknewsclient.domain.entities.Post
import com.olya.milakina.vknewsclient.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Subcomponent

@CommentsScreenScope
@Subcomponent(
    modules = [
        CommentsScreenDataModule::class,
        CommentsScreenViewModelModule::class
    ]
)
internal interface CommentsScreenComponent {

    fun getCommentsViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance post: Post): CommentsScreenComponent
    }
}
