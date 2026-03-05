package com.olya.milakina.vknewsclient.domain.usecases

import com.olya.milakina.vknewsclient.domain.entities.AuthState
import com.olya.milakina.vknewsclient.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

internal class GetAuthStateUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthState()
    }
}
