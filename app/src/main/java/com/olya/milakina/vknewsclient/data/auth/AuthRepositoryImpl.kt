package com.olya.milakina.vknewsclient.data.auth

import android.content.Context
import com.olya.milakina.vknewsclient.data.SecurePrefs
import com.olya.milakina.vknewsclient.domain.repositories.AuthRepository
import com.olya.milakina.vknewsclient.domain.entities.AuthState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class AuthRepositoryImpl(context: Context): AuthRepository {

    private val prefs = SecurePrefs(context)
    private val scope = CoroutineScope(Dispatchers.Default)
    private val checkAuthStateFlow = MutableSharedFlow<Unit>(replay = 1)

    private val isAuthorized = flow {
        checkAuthStateFlow.emit(Unit)
        checkAuthStateFlow.collect {
            val isAuth = prefs.getBoolean(SecurePrefs.IS_AUTHORIZED)
            when(isAuth) {
                true -> emit(AuthState.Authorized)
                else -> emit(AuthState.UnAuthorized)
            }
        }
    }.catch {
        emit(AuthState.UnAuthorized)
    }.stateIn(
        scope = scope,
        started = SharingStarted.Lazily,
        initialValue = AuthState.UnAuthorized
    )

    override fun getAuthState() = isAuthorized

    override suspend fun loginClicked() {
        prefs.putBoolean(SecurePrefs.IS_AUTHORIZED, true)
        checkAuthStateFlow.emit(Unit)
    }
}
