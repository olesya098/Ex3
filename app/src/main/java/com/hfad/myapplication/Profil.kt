package com.hfad.myapplication

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import coil.compose.rememberAsyncImagePainter

@Composable
fun Profil (navController: NavController){
var selectImage by remember { mutableStateOf<Uri?>(null) }

    val imagePicer = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {uri ->
            uri?.let {
                selectImage = it
                navController.navigate(
                    route = Screen.Photo.createRoute(Uri.encode(it.toString())),
                    navOptions = NavOptions.Builder()
                        .setLaunchSingleTop(true)
                        .build()
                )
            }

        }
    )
}