package com.example.chatverse.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun navgraph() {

    var navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = screen.login.route)
    {

    }
}


sealed class screen(val route:String)
{
    object login : screen(route = "login")
    object signup : screen(route = "signup")

}