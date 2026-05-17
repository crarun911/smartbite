package com.smartbite.shared.network

import com.smartbite.shared.core.MealResult
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.*

class SmartBiteApi {

    suspend fun analyzeImage(imageBytes: ByteArray): MealResult {
        val response: HttpResponse = ApiClient.http.post("$BASE_URL/analyze") {
            contentType(ContentType.MultiPart.FormData)

            setBody(
                MultiPartFormDataContent(
                    formData {
                        append(
                            "file",
                            imageBytes,
                            Headers.build {
                                append(HttpHeaders.ContentType, "image/jpeg")
                                append(HttpHeaders.ContentDisposition, "filename=\"meal.jpg\"")
                            }
                        )
                    }
                )
            )
        }

        val json = Json.decodeFromString<MealResult>(response.bodyAsText())
        return json
    }
}
