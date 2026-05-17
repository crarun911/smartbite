package com.smartbite.backend.services

import com.smartbite.backend.models.MealResult
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.*

object VisionService {

    private val client = HttpClient()
    private const val API_KEY = "YOUR_OPENAI_API_KEY"

    suspend fun analyze(imageBytes: ByteArray): MealResult {

        val base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes)

        val response: HttpResponse = client.post("[api.openai.com](https://api.openai.com/v1/chat/completions)") {
            header("Authorization", "Bearer $API_KEY")
            contentType(ContentType.Application.Json)

            setBody(
                buildJsonObject {
                    put("model", "gpt-4o")
                    putJsonArray("messages") {
                        addJsonObject {
                            put("role", "user")
                            putJsonArray("content") {
                                addJsonObject {
                                    put("type", "input_text")
                                    put("text", """
                                        Analyze the meal in this image.
                                        Return ONLY JSON with:
                                        foodName, calories, protein, fat, carbs
                                    """.trimIndent())
                                }
                                addJsonObject {
                                    put("type", "input_image")
                                    putJsonObject("image_url") {
                                        put("url", "data:image/jpeg;base64,$base64Image")
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }

        val jsonText =
            Json.parseToJsonElement(response.bodyAsText())
                .jsonObject["choices"]!!
                .jsonArray[0]
                .jsonObject["message"]!!
                .jsonObject["content"]!!
                .jsonPrimitive.content

        val parsed = Json.parseToJsonElement(jsonText).jsonObject

        return MealResult(
            foodName = parsed["foodName"]!!.jsonPrimitive.content,
            calories = parsed["calories"]!!.jsonPrimitive.int,
            protein = parsed["protein"]!!.jsonPrimitive.double,
            fat = parsed["fat"]!!.jsonPrimitive.double,
            carbs = parsed["carbs"]!!.jsonPrimitive.double
        )
    }
}
