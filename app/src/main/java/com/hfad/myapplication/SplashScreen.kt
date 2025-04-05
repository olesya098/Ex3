package com.hfad.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        delay(3000L)
        if (Internet.isInternet(context)){
            navController.navigate(Screen.NoInternet.route)
        }else{
            navController.navigate(Screen.NoInternet.route)
        }
    }
    Column {
        Text(
            text = "SplashScreen"
        )
    }
}