package com.pedrobaonza.spaceflight.utils

import com.pedrobaonza.spaceflight.data.api.SpaceNewsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.spaceflightnewsapi.net/v4/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SpaceNewsApiService by lazy {
        retrofit.create(SpaceNewsApiService::class.java)
    }
}