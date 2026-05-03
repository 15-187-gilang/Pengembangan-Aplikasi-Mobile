package com.example.tugas6.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugas6.data.model.Article
import com.example.tugas6.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * UI State for the News List Screen.
 */
sealed class NewsUiState {
    object Loading : NewsUiState()
    data class Success(val articles: List<Article>) : NewsUiState()
    data class Error(val message: String) : NewsUiState()
}

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        fetchNews()
    }

    /**
     * Fetches news articles from the repository.
     */
    fun fetchNews() {
        viewModelScope.launch {
            _uiState.value = NewsUiState.Loading
            repository.getNewsArticles()
                .catch { e ->
                    _uiState.value = NewsUiState.Error(e.message ?: "Unknown Error")
                }
                .collect { articles ->
                    _uiState.value = NewsUiState.Success(articles)
                }
        }
    }

    /**
     * Refreshes the news list (Pull-to-refresh).
     */
    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            repository.getNewsArticles()
                .catch { e ->
                    _uiState.value = NewsUiState.Error(e.message ?: "Unknown Error")
                    _isRefreshing.value = false
                }
                .collect { articles ->
                    _uiState.value = NewsUiState.Success(articles)
                    _isRefreshing.value = false
                }
        }
    }
}
