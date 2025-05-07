package com.finalretrofittest.testingappdemo.domain.usecase

import com.finalretrofittest.testingappdemo.data.repository.PostRepository
import com.finalretrofittest.testingappdemo.domain.model.Post

class GetPostsUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(): List<Post> {
        return repository.getPosts()
    }
}