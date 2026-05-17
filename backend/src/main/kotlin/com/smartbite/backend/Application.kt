package com.smartbite.backend

import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.cors.routing.*
import com.smartbite.backend.routes.analyzeRoute

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation) { json() }

    install(CORS) {
        anyHost()
        allowHeaders { true }
    }

    routing {
        analyzeRoute()
    }
}
