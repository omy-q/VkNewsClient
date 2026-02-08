package com.olya.milakina.vknewsclient.presentation.main

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.olya.milakina.vknewsclient.navigation.MainScreenNavGraph
import com.olya.milakina.vknewsclient.navigation.rememberNavigationState
import com.olya.milakina.vknewsclient.presentation.favorite.FavoriteScreen
import com.olya.milakina.vknewsclient.presentation.home.HomeScreen
import com.olya.milakina.vknewsclient.presentation.home.comments.CommentsScreen
import com.olya.milakina.vknewsclient.presentation.profile.ProfileScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Log.d("TEST", "MainScreen")

    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = modifier
            ) {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile
                )

                items.forEach { item ->
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (!selected) navigationState.navigateTo(item.screen.route)
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
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        MainScreenNavGraph(
            navHostController = navigationState.navHostController,
            favoriteScreenContent = { FavoriteScreen(modifier = Modifier.padding(innerPadding)) },
            profileScreenContent = { ProfileScreen(modifier = Modifier.padding(innerPadding)) },
            postsScreenContent = {
                HomeScreen(
                    onCommentClickListener = { navigationState.navigateToComments(it) },
                    modifier = Modifier.padding(innerPadding)
                )
            },
            commentsScreenContent = { post ->
                CommentsScreen(
                    post = post,
                    onBackPressed = { navigationState.navHostController.popBackStack() },
                    modifier = Modifier.padding(innerPadding)
                )
            }
        )
    }
}
