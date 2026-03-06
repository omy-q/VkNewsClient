package com.olya.milakina.vknewsclient.di

import android.content.Context
import com.olya.milakina.vknewsclient.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
internal interface ApplicationComponent {

    fun getViewModelFactory(): ViewModelFactory
    fun getCommentsScreenComponentFactory(): CommentsScreenComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}
