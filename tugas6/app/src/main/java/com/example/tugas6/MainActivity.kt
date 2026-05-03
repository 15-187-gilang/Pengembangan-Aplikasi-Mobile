package com.example.tugas6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tugas6.data.api.NewsApiService
import com.example.tugas6.data.repository.NewsRepository
import com.example.tugas6.ui.NewsDetailScreen
import com.example.tugas6.ui.NewsListScreen
import com.example.tugas6.ui.NewsUiState
import com.example.tugas6.ui.NewsViewModel
import com.example.tugas6.ui.theme.Tugas6Theme
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Initialize Retrofit & Repository (Manual DI for simplicity)
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val apiService = Retrofit.Builder()
            .baseUrl(NewsApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NewsApiService::class.java)

        val repository = NewsRepository(apiService)

        setContent {
            Tugas6Theme {
                // 2. Initialize ViewModel with Factory
                val viewModel: NewsViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return NewsViewModel(repository) as T
                        }
                    }
                )

                NewsApp(viewModel)
            }
        }
    }
}

@Composable
fun NewsApp(viewModel: NewsViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "news_list") {
        // News List Screen
        composable("news_list") {
            NewsListScreen(
                viewModel = viewModel,
                onArticleClick = { article ->
                    navController.navigate("news_detail/${article.id}")
                }
            )
        }

        // News Detail Screen
        composable(
            route = "news_detail/{articleId}",
            arguments = listOf(navArgument("articleId") { type = NavType.IntType })
        ) { backStackEntry ->
            val articleId = backStackEntry.arguments?.getInt("articleId")
            val uiState = viewModel.uiState.value

            if (uiState is NewsUiState.Success) {
                val article = uiState.articles.find { it.id == articleId }
                article?.let {
                    NewsDetailScreen(
                        article = it,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
