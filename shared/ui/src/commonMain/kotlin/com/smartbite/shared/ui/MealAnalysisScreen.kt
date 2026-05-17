package com.smartbite.shared.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

@Composable
fun MealAnalysisScreen(
    viewModel: MealAnalysisViewModel,
    photo: ImageBitmap?,
    onRetake: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (photo != null) {
            Image(
                bitmap = photo,
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }

        Spacer(Modifier.height(16.dp))

        when(state) {
            is MealAnalysisUiState.Idle -> {
                Text("Ready to analyze your meal")
            }

            is MealAnalysisUiState.Loading -> {
                CircularProgressIndicator()
            }

            is MealAnalysisUiState.Success -> {
                val meal = (state as MealAnalysisUiState.Success).result
                Text("Food: ${meal.foodName}")
                Text("Calories: ${meal.calories}")
                Text("Protein: ${meal.protein} g")
                Text("Fat: ${meal.fat} g")
                Text("Carbs: ${meal.carbs} g")
                Spacer(Modifier.height(16.dp))
                Button(onClick = onRetake) { Text("Analyze Another") }
            }

            is MealAnalysisUiState.Error -> {
                Text("Error: ${(state as MealAnalysisUiState.Error).message}")
                Spacer(Modifier.height(16.dp))
                Button(onClick = onRetake) { Text("Try Again") }
            }
        }
    }
}
