package com.example.tugas2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tugas2.ui.theme.Tugas2Theme
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class MainActivity : ComponentActivity() {
    private val simulator = NewsFeedSimulator()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tugas2Theme {
                NewsFeedScreen(simulator)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsFeedScreen(simulator: NewsFeedSimulator) {
    val displayedNews = remember { mutableStateListOf<String>() }
    val readCount by simulator.readCount.collectAsState()
    val targetCategory = "Tech"

    // 1. Flow & Coroutine logic dalam UI
    LaunchedEffect(Unit) {
        simulator.getNewsFlow()
            // 2. Filter berita berdasarkan kategori tertentu
            .filter { it.category == targetCategory }
            // 3. Transform data menjadi format yang ditampilkan
            .map { news ->
                // 5. Coroutines untuk mengambil detail berita secara async
                val detail = async { simulator.fetchNewsDetailAsync(news.id) }.await()
                "[${news.category}] ${news.title}\n$detail"
            }
            .collect { formattedNews ->
                displayedNews.add(0, formattedNews) // Masukkan ke urutan teratas
                simulator.incrementReadCount()
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("News Feed Simulator") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // 4. Menampilkan StateFlow (jumlah berita yang sudah dibaca)
            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Berita $targetCategory dibaca: $readCount",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Feed Terbaru:",
                style = MaterialTheme.typography.labelLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(displayedNews) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
