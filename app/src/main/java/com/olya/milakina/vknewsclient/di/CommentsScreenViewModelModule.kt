package com.olya.milakina.vknewsclient.di

import androidx.lifecycle.ViewModel
import com.olya.milakina.vknewsclient.presentation.home.comments.CommentsScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface CommentsScreenViewModelModule {

    @IntoMap
    @ViewModelKey(CommentsScreenViewModel::class)
    @Binds
    fun bindCommentsScreenViewModel(viewModel: CommentsScreenViewModel): ViewModel
}
