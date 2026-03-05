package com.olya.milakina.vknewsclient.domain.repositories

import com.olya.milakina.vknewsclient.domain.entities.AuthState
import kotlinx.coroutines.flow.StateFlow

internal interface AuthRepository {

    fun getAuthState(): StateFlow<AuthState>

    suspend fun loginClicked()
}
