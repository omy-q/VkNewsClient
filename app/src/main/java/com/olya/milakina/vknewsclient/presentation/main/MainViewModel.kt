package com.olya.milakina.vknewsclient.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.olya.milakina.vknewsclient.data.auth.AuthRepository
import com.olya.milakina.vknewsclient.data.auth.AuthRepositoryImpl
import com.olya.milakina.vknewsclient.domain.AuthState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AuthRepository = AuthRepositoryImpl(application)

    val authState: StateFlow<AuthState> = repository.isAuthorized

    fun onLoginClicked() {
        viewModelScope.launch {
            repository.loginClicked()
        }
    }
}


