package com.pedrobaonza.spaceflight.data.api

import com.pedrobaonza.spaceflight.data.model.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceNewsApiService {

    @GET("articles")
    suspend fun getArticles(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0
    ): Response<ArticlesResponse>
}