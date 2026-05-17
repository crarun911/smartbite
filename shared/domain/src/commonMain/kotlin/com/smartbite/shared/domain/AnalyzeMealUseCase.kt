package com.smartbite.shared.domain

import com.smartbite.shared.core.MealResult
import com.smartbite.shared.data.MealRepository
import com.smartbite.shared.core.AppDispatchers
import kotlinx.coroutines.withContext

class AnalyzeMealUseCase(
    private val repository: MealRepository
) {

    suspend operator fun invoke(imageBytes: ByteArray): MealResult =
        withContext(AppDispatchers.io) {
            repository.analyzeMeal(imageBytes)
        }
}
