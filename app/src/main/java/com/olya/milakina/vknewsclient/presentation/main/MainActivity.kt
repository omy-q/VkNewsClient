package com.olya.milakina.vknewsclient.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olya.milakina.vknewsclient.domain.entities.AuthState
import com.olya.milakina.vknewsclient.presentation.getApplicationComponent
import com.olya.milakina.vknewsclient.presentation.login.LoginScreen
import com.olya.milakina.vknewsclient.ui.theme.VkNewsClientTheme

internal class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val component = getApplicationComponent()
            val viewModel: MainViewModel = viewModel(factory = component.getViewModelFactory())
            val authState = viewModel.authState.collectAsState(AuthState.Authorized)

            VkNewsClientTheme {
                when (authState.value) {
                    AuthState.Authorized -> {
                        MainScreen()
                    }

                    AuthState.UnAuthorized -> {
                        LoginScreen(
                            onLoginClick = viewModel::onLoginClicked
                        )
                    }
                }
            }
        }
    }
}
