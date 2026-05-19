package com.smartbite.shared.core

import kotlinx.serialization.Serializable

@Serializable data class MealResult( val foodName: String, val calories: Int, val protein: Double, val fat: Double, val carbs: Double )