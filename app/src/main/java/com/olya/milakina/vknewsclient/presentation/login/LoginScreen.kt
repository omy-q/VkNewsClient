package com.olya.milakina.vknewsclient.presentation.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.olya.milakina.vknewsclient.R
import com.olya.milakina.vknewsclient.ui.theme.DarkBlue

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Log.d("TEST", "LoginScreen")

    Box(
        modifier = modifier
            .systemBarsPadding()
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_login),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )

        Button(
            onClick = onLoginClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkBlue,
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.login_button),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
