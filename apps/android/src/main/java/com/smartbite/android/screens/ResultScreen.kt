package com.smartbite.android.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.smartbite.shared.ui.MealAnalysisViewModel
import com.smartbite.shared.ui.MealAnalysisUiState
import org.koin.androidx.compose.koinViewModel
import android.graphics.BitmapFactory

object ResultScreen {
    // Temporary holder
    var imageBytes: ByteArray? = null
}

@Composable
fun ResultScreen(
    navController: NavController? = null,
    viewModel: MealAnalysisViewModel = koinViewModel()
) {
    val bytes = ResultScreen.imageBytes
    if (bytes == null) {
        Text("No image provided")
        return
    }

    // Convert bytes to ImageBitmap for UI preview
    val bitmap: ImageBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size).asImageBitmap()

    // ViewModel state
    val state by viewModel.state.collectAsState()

    // Trigger analysis once when screen opens
    LaunchedEffect(bytes) {
        viewModel.submitImage(bytes)
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Photo preview
        Image(
            bitmap = bitmap,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )

        Spacer(Modifier.height(24.dp))

        when (state) {

            is MealAnalysisUiState.Loading -> {
                Text("Analyzing your meal...")
                Spacer(Modifier.height(16.dp))
                CircularProgressIndicator()
            }

            is MealAnalysisUiState.Success -> {
                val meal = (state as MealAnalysisUiState.Success).result
                Text("Food: ${meal.foodName}")
                Text("Calories: ${meal.calories}")
                Text("Protein: ${meal.protein} g")
                Text("Fat: ${meal.fat} g")
                Text("Carbs: ${meal.carbs} g")

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = {
                        viewModel.reset()
                        navController?.navigate("camera") {
                            popUpTo("camera") { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Analyze Another Meal")
                }
            }

            is MealAnalysisUiState.Error -> {
                val message = (state as MealAnalysisUiState.Error).message
                Text("Error: $message")

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = {
                        viewModel.reset()
                        navController?.navigate("camera") {
                            popUpTo("camera") { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Try Again")
                }
            }

            MealAnalysisUiState.Idle -> {
                // Should not occur, but safe fallback
                Text("Ready")
            }
        }
    }
}
