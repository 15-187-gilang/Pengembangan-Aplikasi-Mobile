package com.example.tugas6.data.api

import com.example.tugas6.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface for fetching news from Spaceflight News API.
 */
interface NewsApiService {
    @GET("articles")
    suspend fun getArticles(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): NewsResponse

    companion object {
        const val BASE_URL = "https://api.spaceflightnewsapi.net/v4/"
    }
}
