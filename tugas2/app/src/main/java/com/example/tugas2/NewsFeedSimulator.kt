package com.example.tugas2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// Data class untuk merepresentasikan Berita
data class News(
    val id: Int,
    val title: String,
    val category: String
)

// Simulator News Feed
class NewsFeedSimulator {
    // 4. StateFlow untuk menyimpan jumlah berita yang sudah dibaca
    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    // 1. Flow yang mensimulasikan data berita baru setiap 2 detik
    fun getNewsFlow(): Flow<News> = flow {
        val newsList = listOf(
            News(1, "Kotlin 2.0 Released", "Tech"),
            News(2, "New Parks Opening", "Local"),
            News(3, "AI in 2024", "Tech"),
            News(4, "Healthy Eating Tips", "Health"),
            News(5, "SpaceX Launch Success", "Tech"),
            News(6, "Football Finals Tonight", "Sports"),
            News(7, "New Smartphone Model", "Tech")
        )
        var index = 0
        while (true) {
            emit(newsList[index % newsList.size])
            index++
            delay(2000) // Delay 2 detik
        }
    }

    // 5. Coroutines untuk mengambil detail berita secara async
    suspend fun fetchNewsDetailAsync(newsId: Int): String = withContext(Dispatchers.IO) {
        delay(1000) // Simulasi delay network
        "Isi detail lengkap untuk berita ID #$newsId"
    }

    fun incrementReadCount() {
        _readCount.value += 1
    }
}

// Main function (Struktur project Kotlin standar)
fun main() = runBlocking {
    val simulator = NewsFeedSimulator()
    val scope = CoroutineScope(Dispatchers.Default + Job())
    val targetCategory = "Tech" // Filter kategori

    println("=== News Feed Simulator Dimulai ===")
    println("Memfilter berita dengan kategori: $targetCategory\n")

    // Launch coroutine untuk memproses flow berita
    val flowJob = scope.launch {
        simulator.getNewsFlow()
            // 2. Filter berita berdasarkan kategori tertentu
            .filter { it.category == targetCategory }
            // 3. Transform data menjadi format yang ditampilkan
            .map { news ->
                // Mengambil detail secara async menggunakan coroutine
                val detail = async { simulator.fetchNewsDetailAsync(news.id) }
                "BERITA TERBARU: [${news.category}] ${news.title}\nDetail: ${detail.await()}"
            }
            .collect { formattedNews ->
                println(formattedNews)
                println("-----------------------------------")
                simulator.incrementReadCount()
            }
    }

    // Launch coroutine untuk memantau StateFlow (jumlah berita dibaca)
    val stateJob = scope.launch {
        simulator.readCount.collect { count ->
            if (count > 0) {
                println(">>> Notifikasi: Anda telah membaca $count berita kategori $targetCategory")
            }
        }
    }

    // Jalankan simulator selama 15 detik untuk demonstrasi
    delay(15000)
    
    // Cleanup
    flowJob.cancel()
    stateJob.cancel()
    println("\n=== News Feed Simulator Berhenti ===")
}
