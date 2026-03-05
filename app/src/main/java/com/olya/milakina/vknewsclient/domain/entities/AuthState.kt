package com.olya.milakina.vknewsclient.domain.entities

internal sealed interface AuthState {
    data object Authorized : AuthState
    data object UnAuthorized : AuthState
}
