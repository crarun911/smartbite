package com.smartbite.shared.network

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.*

expect fun provideEngine(): HttpClientEngineFactory<*>

object ApiClient {
    val http = HttpClient(provideEngine()) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = false
                }
            )
        }
    }
}
