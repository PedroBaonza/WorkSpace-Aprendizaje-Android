package com.finalretrofittest.testingappdemo.data.repository

import com.finalretrofittest.testingappdemo.data.api.ApiService
import com.finalretrofittest.testingappdemo.domain.model.Post

class PostRepository(private val apiService: ApiService) {

    suspend fun getPosts(): List<Post> {
        return apiService.getPosts()
    }

}