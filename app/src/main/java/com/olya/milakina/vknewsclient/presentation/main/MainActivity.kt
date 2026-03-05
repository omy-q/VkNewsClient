package com.olya.milakina.vknewsclient.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olya.milakina.vknewsclient.domain.entities.AuthState
import com.olya.milakina.vknewsclient.presentation.NewsClientApplication
import com.olya.milakina.vknewsclient.presentation.ViewModelFactory
import com.olya.milakina.vknewsclient.presentation.login.LoginScreen
import com.olya.milakina.vknewsclient.ui.theme.VkNewsClientTheme
import javax.inject.Inject

internal class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as NewsClientApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkNewsClientTheme {
                val viewModel: MainViewModel = viewModel(factory = viewModelFactory)
                val authState = viewModel.authState.collectAsState(AuthState.Authorized)
                when (authState.value) {
                    AuthState.Authorized -> {
                        MainScreen(viewModelFactory)
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
