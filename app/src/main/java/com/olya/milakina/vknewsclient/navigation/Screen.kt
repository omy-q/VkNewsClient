package com.olya.milakina.vknewsclient.navigation

import android.net.Uri
import com.olya.milakina.vknewsclient.domain.Post
import com.google.gson.Gson

sealed class Screen(val route: String) {

    object Home : Screen(route = ROUTE_HOME)
    object Favorite : Screen(route = ROUTE_FAVORITE)
    object Profile : Screen(route = ROUTE_PROFILE)
    object Posts : Screen(route = ROUTE_POSTS)
    object Comments : Screen(route = ROUTE_COMMENTS) {
        private const val ROUTE_FOR_ARGS = "comments"
        fun getRouteWithArgs(post: Post): String {
            val postJson = Gson().toJson(post)
            return "$ROUTE_FOR_ARGS/${postJson.encode()}"
        }
    }

    companion object {
        const val KEY_POST = "post"
        const val ROUTE_HOME = "home"
        const val ROUTE_FAVORITE = "favorite"
        const val ROUTE_PROFILE = "profile"
        const val ROUTE_POSTS = "posts"
        const val ROUTE_COMMENTS = "comments/{$KEY_POST}"
    }
}

fun String.encode(): String? = Uri.encode(this)
