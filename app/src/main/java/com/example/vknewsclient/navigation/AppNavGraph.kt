package com.example.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    favoriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.NewsFeed.route,

        modifier = modifier
    ) {
        composable(route = Screen.NewsFeed.route) {
            homeScreenContent.invoke()
        }

        composable(route = Screen.Favorite.route) {
            favoriteScreenContent.invoke()
        }

        composable(route = Screen.Profile.route) {
            profileScreenContent.invoke()
        }
    }
}
