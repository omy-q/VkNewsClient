package com.olya.milakina.vknewsclient.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.olya.milakina.vknewsclient.di.ApplicationComponent

@Composable
internal fun getApplicationComponent(): ApplicationComponent {
    Log.d("TEST", "RECOMPOSITION getApplicationComponent")
    return (LocalContext.current.applicationContext as NewsClientApplication).component
}