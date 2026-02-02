package com.example.vknewsclient

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vknewsclient.navigation.AppNavGraph
import com.example.vknewsclient.navigation.rememberNavigationState
import com.example.vknewsclient.ui.elements.NavigationItem
import com.example.vknewsclient.ui.favorite.FavoriteScreen
import com.example.vknewsclient.ui.home.HomeScreen
import com.example.vknewsclient.ui.home.comments.CommentsScreen
import com.example.vknewsclient.ui.profile.ProfileScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Log.d("TEST", "MainScreen")

    val commentsToPostId: MutableState<Int?> = remember { mutableStateOf(null) }
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            VkNewsNavigationBar(
                currentRoute = currentRoute,
                onNavigationItemClickListener = { navigationState.navigateTo(it.screen.route) }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent = {
                if (commentsToPostId.value == null) {
                    HomeScreen(
                        onCommentClickListener = {
                            commentsToPostId.value = it
                        }
                    )
                } else {
                    CommentsScreen(
                        postId = commentsToPostId.value!!,
                        onBackPressed = {
                            commentsToPostId.value = null
                        }
                    )
                }
            },
            favoriteScreenContent = { FavoriteScreen() },
            profileScreenContent = { ProfileScreen() },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun VkNewsNavigationBar(
    currentRoute: String?,
    onNavigationItemClickListener: (NavigationItem) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = modifier
    ) {
        val items = listOf(
            NavigationItem.Home,
            NavigationItem.Favorite,
            NavigationItem.Profile
        )

        items.forEach { item ->
            NavigationBarItem(
                selected = item.screen.route == currentRoute,
                onClick = { onNavigationItemClickListener.invoke(item) },
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
