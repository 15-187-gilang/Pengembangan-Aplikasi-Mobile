package com.example.tugas3.model

import java.io.Serializable

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val isFavorite: Boolean = false
) : Serializable

val dummyNotes = listOf(
    Note(1, "Belanja", "Beli susu, telur, dan roti."),
    Note(2, "Tugas Kuliah", "Kerjakan tugas Mobile Programming.", isFavorite = true),
    Note(3, "Ide Bisnis", "Membuat aplikasi pencatat keuangan."),
    Note(4, "Catatan Harian", "Hari ini cuaca sangat cerah.", isFavorite = true),
)
