package com.smartbite.shared.network

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

actual fun provideEngine(): HttpClientEngineFactory<*> = OkHttp
