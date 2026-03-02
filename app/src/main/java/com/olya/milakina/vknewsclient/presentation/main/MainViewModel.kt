package com.olya.milakina.vknewsclient.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.olya.milakina.vknewsclient.data.SecurePrefs

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = SecurePrefs(application)

    private val _authState = MutableLiveData<AuthState>(AuthState.UnAuthorized)
    val authState: LiveData<AuthState> = _authState

    init {
        _authState.value = when (prefs.getBoolean(SecurePrefs.IS_AUTHORIZED)) {
            true -> AuthState.Authorized
            else -> AuthState.UnAuthorized
        }
    }

    fun onLoginClicked() {
        prefs.putBoolean(SecurePrefs.IS_AUTHORIZED, true)
        _authState.value = AuthState.Authorized
    }
}

sealed interface AuthState {
    data object Authorized : AuthState
    data object UnAuthorized : AuthState
}
