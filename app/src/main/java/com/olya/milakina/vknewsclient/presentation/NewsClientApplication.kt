package com.olya.milakina.vknewsclient.presentation

import android.app.Application
import com.olya.milakina.vknewsclient.di.ApplicationComponent
import com.olya.milakina.vknewsclient.di.DaggerApplicationComponent

internal class NewsClientApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(context = this)
    }
}
