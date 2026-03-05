package com.olya.milakina.vknewsclient.domain

sealed interface AuthState {
    data object Authorized : AuthState
    data object UnAuthorized : AuthState
}
