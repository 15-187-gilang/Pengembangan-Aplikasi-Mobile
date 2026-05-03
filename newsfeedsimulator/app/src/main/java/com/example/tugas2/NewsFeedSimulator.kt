package com.example.tugas2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * 1. Data Class untuk struktur data berita
 */
data class News(
    val id: Int,
    val title: String,
    val category: String,
    val content: String
)

/**
 * 3. Transform data menjadi format yang ditampilkan (UI Model)
 */
data class NewsDisplayModel(
    val id: Int,
    val title: String,
    val category: String
)

/**
 * Kelas Manager untuk mensimulasikan News Feed
 */
class NewsFeedManager(private val scope: CoroutineScope) {

    // 4. StateFlow untuk menyimpan jumlah berita yang sudah dibaca
    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    /**
     * 1. Flow yang mensimulasikan data berita baru setiap 2 detik
     */
    fun getRawNewsFlow(): Flow<News> = flow {
        val categories = listOf("Tech", "Health", "Politics", "Sports")
        var id = 1
        while (true) {
            val news = News(
                id = id,
                title = "Berita Utama #$id",
                category = categories.random(),
                content = "Konten lengkap untuk berita nomor $id yang sangat menarik."
            )
            emit(news)
            id++
            delay(2000) // Jeda 2 detik sesuai permintaan
        }
    }

    /**
     * 2. Filter berita berdasarkan kategori tertentu
     * 3. Transformasi data ke NewsDisplayModel
     */
    fun getFilteredNews(category: String): Flow<NewsDisplayModel> {
        return getRawNewsFlow()
            .filter { it.category == category } // Filter kategori
            .map { news ->
                // Transformasi format (Contoh: judul jadi uppercase dan kategori pakai kurung)
                NewsDisplayModel(
                    id = news.id,
                    title = news.title.uppercase(),
                    category = "[${news.category}]"
                )
            }
    }

    /**
     * 5. Coroutines untuk mengambil detail berita secara async
     */
    suspend fun fetchNewsDetail(id: Int): String {
        return withContext(Dispatchers.IO) {
            delay(1000) // Simulasi pengambilan data dari network/database
            "Detail konten untuk Berita ID $id berhasil dimuat secara asynchronous."
        }
    }

    fun incrementReadCount() {
        _readCount.value++
    }
}

/**
 * Main Function untuk menjalankan simulasi (Standard Kotlin Structure)
 */
fun main() = runBlocking {
    val manager = NewsFeedManager(this)
    val filterCategory = "Tech" // Ganti kategori di sini untuk filter berbeda

    println("=== NEWS FEED SIMULATOR STARTING (Filter: $filterCategory) ===")
    println("Menunggu berita baru setiap 2 detik...\n")

    val simulationJob = launch {
        manager.getFilteredNews(filterCategory).collect { news ->
            println(">>> Berita Diterima: ${news.title} ${news.category}")

            // Mengambil detail secara async menggunakan coroutine
            launch {
                val detail = manager.fetchNewsDetail(news.id)
                println("   [ASYNC DETAIL - ID ${news.id}]: $detail")

                // Update StateFlow jumlah berita yang dibaca
                manager.incrementReadCount()
                println("   [STATEFLOW]: Total Berita Dibaca = ${manager.readCount.value}")
            }
        }
    }

    // Jalankan simulasi selama 20 detik, lalu berhenti
    delay(20000)
    simulationJob.cancel()
    println("\n=== SIMULASI BERAKHIR ===")
}
