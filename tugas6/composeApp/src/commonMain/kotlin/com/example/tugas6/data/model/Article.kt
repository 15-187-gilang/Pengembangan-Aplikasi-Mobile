package com.example.tugas6.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: Int,
    val title: String,
    val body: String,
    val imageUrl: String = "https://picsum.photos/seed/${id}/400/300"
)
