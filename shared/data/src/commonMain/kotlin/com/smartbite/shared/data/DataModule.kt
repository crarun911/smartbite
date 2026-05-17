package com.smartbite.shared.data

import com.smartbite.shared.network.SmartBiteApi
import org.koin.dsl.module

val dataModule = module {
    single<MealRepository> { MealRepositoryImpl(get<SmartBiteApi>()) }
}
