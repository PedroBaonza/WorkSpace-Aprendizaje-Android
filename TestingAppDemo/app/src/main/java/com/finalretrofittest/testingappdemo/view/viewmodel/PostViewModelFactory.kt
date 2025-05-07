package com.finalretrofittest.testingappdemo.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.finalretrofittest.testingappdemo.data.api.RetrofitInstance
import com.finalretrofittest.testingappdemo.data.repository.PostRepository
import com.finalretrofittest.testingappdemo.domain.usecase.GetPostsUseCase

class PostViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = PostRepository(RetrofitInstance.api)
        val useCase = GetPostsUseCase(repository)
        return PostViewModel(useCase) as T
    }
}