package com.finalretrofittest.testingappdemo.view.viewmodel

import com.finalretrofittest.testingappdemo.domain.model.Post

sealed class PostUiState {
    object Loading : PostUiState()
    data class Success(val posts: List<Post>) : PostUiState()
    data class Error(val message: String) : PostUiState()
}