package com.example.vknewsclient

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val snackBarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()
    val fabIsVisible = remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = { VkNewsNavigationBar() },
        floatingActionButton = {
            AnimatedVisibility(
                visible = fabIsVisible.value
            ) {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            val action = snackBarHostState.showSnackbar(
                                message = "Ths is Snack Bar",
                                actionLabel = "Hide FAB",
                                duration = SnackbarDuration.Long
                            )

                            if (action == SnackbarResult.ActionPerformed) {
                                fabIsVisible.value = false
                            }
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                }
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}

@Composable
private fun VkNewsNavigationBar(
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = modifier
    ) {
        val selectedItemPosition = remember { mutableIntStateOf(0) }
        val items = listOf(
            NavigationItem.Home,
            NavigationItem.Favorite,
            NavigationItem.Profile
        )

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemPosition.value == index,
                onClick = {
                    selectedItemPosition.value = index
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                },
                label = { Text(text = stringResource(item.titleResId)) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSecondary
                )
            )
        }
    }
}
