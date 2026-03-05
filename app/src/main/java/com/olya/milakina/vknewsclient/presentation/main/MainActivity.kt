package com.olya.milakina.vknewsclient.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olya.milakina.vknewsclient.domain.AuthState
import com.olya.milakina.vknewsclient.presentation.login.LoginScreen
import com.olya.milakina.vknewsclient.ui.theme.VkNewsClientTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkNewsClientTheme {
                val viewModel: MainViewModel = viewModel()
                val authState = viewModel.authState.collectAsState(AuthState.UnAuthorized)
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
