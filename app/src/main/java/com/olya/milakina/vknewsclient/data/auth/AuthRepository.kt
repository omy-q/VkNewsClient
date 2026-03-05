package com.olya.milakina.vknewsclient.data.auth

import com.olya.milakina.vknewsclient.domain.AuthState
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {

    val isAuthorized: StateFlow<AuthState>

    suspend fun loginClicked()
}
