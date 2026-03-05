package com.olya.milakina.vknewsclient.domain.usecases

import com.olya.milakina.vknewsclient.domain.repositories.AuthRepository
import javax.inject.Inject

internal class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() {
        repository.loginClicked()
    }
}
