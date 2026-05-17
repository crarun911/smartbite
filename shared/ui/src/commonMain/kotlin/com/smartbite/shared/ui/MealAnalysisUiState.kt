package com.smartbite.shared.ui

import com.smartbite.shared.core.MealResult

sealed interface MealAnalysisUiState {
    object Idle : MealAnalysisUiState
    object Loading : MealAnalysisUiState
    data class Success(val result: MealResult) : MealAnalysisUiState
    data class Error(val message: String) : MealAnalysisUiState
}
