package com.mbahgojol.data.news

interface NewsRepository {
    suspend fun getNews()
}