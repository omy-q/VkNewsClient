package com.olya.milakina.vknewsclient.di

import androidx.lifecycle.ViewModel
import com.olya.milakina.vknewsclient.presentation.home.HomeScreenViewModel
import com.olya.milakina.vknewsclient.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface ViewModelModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @IntoMap
    @ViewModelKey(HomeScreenViewModel::class)
    @Binds
    fun bindHomeScreenViewModel(viewModel: HomeScreenViewModel): ViewModel
}
