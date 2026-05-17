package com.smartbite.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.smartbite.android.screens.CameraScreen
import com.smartbite.android.screens.ResultScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "camera") {

        composable("camera") {
            CameraScreen(
                onImageCaptured = { bytes ->
                    navController.navigate("result")
                    ResultScreen.imageBytes = bytes
                }
            )
        }

        composable("result") {
            ResultScreen(navController)
        }
    }
}
