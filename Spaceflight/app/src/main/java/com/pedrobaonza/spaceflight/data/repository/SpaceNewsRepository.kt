package com.pedrobaonza.spaceflight.data.repository
import com.pedrobaonza.spaceflight.data.api.SpaceNewsApiService
import com.pedrobaonza.spaceflight.data.model.ArticlesResponse
import retrofit2.Response

class SpaceNewsRepository(
    private val apiService: SpaceNewsApiService
) {

    suspend fun getArticles(limit: Int = 10, offset: Int = 0): Response<ArticlesResponse> {
        return apiService.getArticles(limit, offset)
    }
}