package com.example.tugas6.data.repository

import com.example.tugas6.data.api.NewsApiService
import com.example.tugas6.data.model.Article
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository class to handle data operations.
 * Updated to provide 6 Indonesian news articles as dummy data.
 */
class NewsRepository(private val apiService: NewsApiService) {

    /**
     * Fetches news articles. Returns dummy Indonesian news for this task.
     */
    fun getNewsArticles(): Flow<List<Article>> = flow {
        // Simulasikan delay network agar Loading state terlihat
        delay(1500)
        
        val dummyArticles = listOf(
            Article(
                id = 1,
                title = "Pembangunan IKN Terus Dikebut Menjelang Akhir Tahun",
                description = "Pemerintah memastikan pembangunan infrastruktur dasar di Ibu Kota Nusantara (IKN) berjalan sesuai jadwal untuk menyambut perpindahan ASN.",
                imageUrl = "https://images.unsplash.com/photo-1596402184320-417d7178b2cd?q=80&w=1000",
                publishedAt = "2023-10-25T10:00:00Z",
                newsSite = "Berita Nasional",
                url = "https://example.com/ikn"
            ),
            Article(
                id = 2,
                title = "Timnas Indonesia Raih Kemenangan Bersejarah",
                description = "Skuad Garuda berhasil mengalahkan tim kuat dalam lanjutan kualifikasi Piala Dunia zona Asia dengan skor tipis.",
                imageUrl = "https://images.unsplash.com/photo-1574629810360-7efbbe195018?q=80&w=1000",
                publishedAt = "2023-10-24T21:00:00Z",
                newsSite = "Bola Indo",
                url = "https://example.com/timnas"
            ),
            Article(
                id = 3,
                title = "Wisata Bali Tetap Jadi Favorit Wisatawan Mancanegara",
                description = "Angka kunjungan turis asing ke Bali terus meningkat melampaui target tahunan, memberikan dampak positif bagi ekonomi lokal.",
                imageUrl = "https://images.unsplash.com/photo-1537996194471-e657df975ab4?q=80&w=1000",
                publishedAt = "2023-10-24T08:30:00Z",
                newsSite = "Travel Asia",
                url = "https://example.com/bali"
            ),
            Article(
                id = 4,
                title = "Ekonomi Indonesia Tumbuh Stabil di Angka 5 Persen",
                description = "Badan Pusat Statistik melaporkan pertumbuhan ekonomi Indonesia tetap resilien di tengah ketidakpastian global.",
                imageUrl = "https://images.unsplash.com/photo-1526304640581-d334cdbbf45e?q=80&w=1000",
                publishedAt = "2023-10-23T15:45:00Z",
                newsSite = "Ekonomi Bisnis",
                url = "https://example.com/ekonomi"
            ),
            Article(
                id = 5,
                title = "Peluncuran Satelit Satria-1 Perluas Akses Internet",
                description = "Satelit multifungsi milik Indonesia sukses diluncurkan untuk memperkuat konektivitas di wilayah 3T (Terdepan, Terluar, Tertinggal).",
                imageUrl = "https://images.unsplash.com/photo-1446776811953-b23d57bd21aa?q=80&w=1000",
                publishedAt = "2023-10-22T12:00:00Z",
                newsSite = "Tekno Indo",
                url = "https://example.com/satelit"
            ),
            Article(
                id = 6,
                title = "Kuliner Nusantara Mendunia Melalui Program Indonesia Spice Up The World",
                description = "Rendang dan Nasi Goreng kembali dinobatkan sebagai salah satu makanan terenak di dunia versi survei internasional.",
                imageUrl = "https://images.unsplash.com/photo-1504674900247-0877df9cc836?q=80&w=1000",
                publishedAt = "2023-10-21T09:15:00Z",
                newsSite = "Info Kuliner",
                url = "https://example.com/kuliner"
            )
        )
        emit(dummyArticles)
    }
}
