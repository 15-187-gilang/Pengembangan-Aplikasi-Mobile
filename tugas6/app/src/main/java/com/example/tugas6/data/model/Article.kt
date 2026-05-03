package com.example.tugas6.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data model for an Article.
 * Using Spaceflight News API structure as an example.
 */
data class Article(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("summary") val description: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("published_at") val publishedAt: String,
    @SerializedName("news_site") val newsSite: String,
    @SerializedName("url") val url: String
)

data class NewsResponse(
    @SerializedName("results") val results: List<Article>
)
