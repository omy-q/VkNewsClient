package com.olya.milakina.vknewsclient.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olya.milakina.vknewsclient.domain.entities.AuthState
import com.olya.milakina.vknewsclient.domain.usecases.GetAuthStateUseCase
import com.olya.milakina.vknewsclient.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class MainViewModel @Inject constructor(
    getAuthStateUseCase: GetAuthStateUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    val authState: StateFlow<AuthState> = getAuthStateUseCase()

    fun onLoginClicked() {
        viewModelScope.launch {
            loginUseCase()
        }
    }
}
