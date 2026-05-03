package com.example.tugas3.data

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val isFavorite: Boolean = false
)
