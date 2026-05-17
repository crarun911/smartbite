package com.smartbite.shared.domain

import com.smartbite.shared.data.MealRepository
import org.koin.dsl.module

val domainModule = module {
    single { AnalyzeMealUseCase(get<MealRepository>()) }
}
