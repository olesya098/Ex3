package com.hfad.myapplication

import android.media.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import java.util.Stack

sealed class Screen(val route: String) {
    object SplashScreen : Screen("SplashScreen")
    object NoInternet : Screen("NoInternet")
    object Profil : Screen("Profil")
    object Photo : Screen("Photo/{imageUri}") {
        fun createRoute(imageUri: String) = "Photo/$imageUri"
    }
}


@Composable
fun Navigatia() {
    val navController = rememberNavController()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        if (Internet.isInternet(context)) {
            navController.navigate(Screen.SplashScreen.route)
        } else {
            navController.navigate(Screen.NoInternet.route)
        }
    }
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(
            Screen.SplashScreen.route
        ) {
            SplashScreen(navController)
        }
        composable(
            Screen.NoInternet.route
        ) {
            NoInternet(navController)
        }
        composable(Screen.Profil.route) {
            Profil(navController)
        }
        composable(
            route = Screen.Photo.route,
            arguments = listOf(
                navArgument("imageUri") {
                    type = NavType.StringType
                }
            )
        ) { Stack ->
            val imageUri = Stack.arguments?.getString("imageUri") ?: ""
            Photo(navController, imageUri)

        }

    }
}