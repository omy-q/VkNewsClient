package com.olya.milakina.vknewsclient.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.olya.milakina.vknewsclient.domain.repositories.AuthRepository
import com.olya.milakina.vknewsclient.data.auth.AuthRepositoryImpl
import com.olya.milakina.vknewsclient.domain.entities.AuthState
import com.olya.milakina.vknewsclient.domain.usecases.GetAuthStateUseCase
import com.olya.milakina.vknewsclient.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AuthRepository = AuthRepositoryImpl(application)
    private val getAuthStateUseCase = GetAuthStateUseCase(repository)
    private val loginUseCase = LoginUseCase(repository)

    val authState: StateFlow<AuthState> = getAuthStateUseCase()

    fun onLoginClicked() {
        viewModelScope.launch {
            loginUseCase()
        }
    }
}
