package com.example.tugas6

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tugas6.data.model.Article
import com.example.tugas6.data.remote.NewsApi
import com.example.tugas6.data.remote.createHttpClient
import com.example.tugas6.data.repository.NewsRepository
import com.example.tugas6.ui.ArticleDetailScreen
import com.example.tugas6.ui.NewsListScreen
import com.example.tugas6.ui.NewsViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        val httpClient = remember { createHttpClient() }
        val api = remember { NewsApi(httpClient) }
        val repository = remember { NewsRepository(api) }
        val viewModel: NewsViewModel = viewModel { NewsViewModel(repository) }
        
        var selectedArticle by remember { mutableStateOf<Article?>(null) }

        NavHost(
            navController = navController,
            startDestination = "list"
        ) {
            composable("list") {
                NewsListScreen(
                    viewModel = viewModel,
                    onArticleClick = { article ->
                        selectedArticle = article
                        navController.navigate("detail")
                    }
                )
            }
            composable("detail") {
                selectedArticle?.let { article ->
                    ArticleDetailScreen(
                        article = article,
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}
