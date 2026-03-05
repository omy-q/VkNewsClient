package com.olya.milakina.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.olya.milakina.vknewsclient.domain.entities.Post
import com.google.gson.Gson

fun NavGraphBuilder.homeScreenNavGraph(
    postsScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (post: Post) -> Unit,
) {
    navigation(
        startDestination = Screen.Posts.route,
        route = Screen.Home.route
    ) {
        composable(route = Screen.Posts.route) {
            postsScreenContent.invoke()
        }
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(
                    name = Screen.KEY_POST,
                    builder = { type = NavType.StringType }
                )
            )
        ) {
            val postJson = it.arguments?.getString(Screen.KEY_POST) ?: ""
            val post = Gson().fromJson(postJson, Post::class.java)
            commentsScreenContent.invoke(post)
        }
    }
}
