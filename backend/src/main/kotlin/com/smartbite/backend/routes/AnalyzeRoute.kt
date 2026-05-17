package com.smartbite.backend.routes

import com.smartbite.backend.services.VisionService
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*

fun Route.analyzeRoute() {

    post("/analyze") {
        val multipart = call.receiveMultipart()
        var imageBytes: ByteArray? = null

        multipart.forEachPart { part ->
            if (part is PartData.FileItem) {
                imageBytes = part.streamProvider().readBytes()
            }
            part.dispose()
        }

        if (imageBytes == null) {
            call.respond(HttpStatusCode.BadRequest, "No image uploaded")
            return@post
        }

        val result = VisionService.analyze(imageBytes!!)
        call.respond(result)
    }
}
