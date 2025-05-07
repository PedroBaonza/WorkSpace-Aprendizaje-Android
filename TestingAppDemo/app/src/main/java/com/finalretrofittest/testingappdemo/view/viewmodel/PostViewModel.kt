package com.finalretrofittest.testingappdemo.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finalretrofittest.testingappdemo.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<PostUiState>(PostUiState.Loading)
    val uiState: StateFlow<PostUiState> = _uiState

    fun fetchPosts() {
        viewModelScope.launch {
            _uiState.value = PostUiState.Loading
            try {
                val posts = getPostsUseCase()
                _uiState.value = PostUiState.Success(posts)
            } catch (e: Exception) {
                _uiState.value = PostUiState.Error("Error: ${e.localizedMessage}")
            }
        }
    }
}