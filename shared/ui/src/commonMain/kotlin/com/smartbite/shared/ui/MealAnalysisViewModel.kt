package com.smartbite.shared.ui

import com.smartbite.shared.domain.AnalyzeMealUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MealAnalysisViewModel(
    private val analyzeMeal: AnalyzeMealUseCase,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    private val scope = CoroutineScope(SupervisorJob() + mainDispatcher)

    private val _state = MutableStateFlow<MealAnalysisUiState>(MealAnalysisUiState.Idle)
    val state: StateFlow<MealAnalysisUiState> = _state.asStateFlow()

    fun submitImage(bytes: ByteArray) {
        _state.value = MealAnalysisUiState.Loading

        scope.launch {
            try {
                val result = analyzeMeal(bytes)
                _state.value = MealAnalysisUiState.Success(result)
            } catch (e: Exception) {
                _state.value = MealAnalysisUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun reset() {
        _state.value = MealAnalysisUiState.Idle
    }
}
