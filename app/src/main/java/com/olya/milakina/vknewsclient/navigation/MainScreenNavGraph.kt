package com.olya.milakina.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.olya.milakina.vknewsclient.domain.Post

@Composable
fun MainScreenNavGraph(
    navHostController: NavHostController,
    favoriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    postsScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (post: Post) -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {

        homeScreenNavGraph(
            postsScreenContent = postsScreenContent,
            commentsScreenContent = commentsScreenContent
        )

        composable(route = Screen.Favorite.route) {
            favoriteScreenContent.invoke()
        }

        composable(route = Screen.Profile.route) {
            profileScreenContent.invoke()
        }
    }
}
