package com.example.tugas6.data.remote

import com.example.tugas6.data.model.Article
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class NewsApi(private val client: HttpClient) {
    suspend fun getArticles(): List<Article> {
        return client.get("https://jsonplaceholder.typicode.com/posts").body()
    }
}

fun createHttpClient(): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }
}
