package com.smartbite.shared.ui

import com.smartbite.shared.domain.AnalyzeMealUseCase
import org.koin.dsl.module

val uiModule = module {
    single { MealAnalysisViewModel(get<AnalyzeMealUseCase>()) }
}
