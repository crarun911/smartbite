package com.smartbite.shared.data

import com.smartbite.shared.core.MealResult
import com.smartbite.shared.network.SmartBiteApi

class MealRepositoryImpl(
    private val api: SmartBiteApi
) : MealRepository {

    override suspend fun analyzeMeal(imageBytes: ByteArray): MealResult {
        return api.analyzeImage(imageBytes)
    }
}
