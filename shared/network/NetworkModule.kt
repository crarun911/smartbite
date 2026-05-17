package com.smartbite.shared.network

import org.koin.dsl.module

val networkModule = module {
    single { SmartBiteApi() }
}
