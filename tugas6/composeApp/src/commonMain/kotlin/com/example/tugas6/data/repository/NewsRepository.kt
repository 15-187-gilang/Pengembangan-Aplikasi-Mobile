package com.example.tugas6.data.repository

import com.example.tugas6.data.model.Article
import com.example.tugas6.data.remote.NewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepository(private val api: NewsApi) {
    fun getArticles(): Flow<Result<List<Article>>> = flow {
        try {
            val articles = api.getArticles()
            emit(Result.success(articles))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
