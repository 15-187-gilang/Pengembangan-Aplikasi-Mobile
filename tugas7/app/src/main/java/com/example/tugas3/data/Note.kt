package com.example.tugas3.data

data class Note(
    val id: Long,
    val title: String,
    val content: String,
    val isFavorite: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
