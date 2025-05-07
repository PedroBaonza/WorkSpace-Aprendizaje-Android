package com.pedrobaonza.spaceflight.ui.articles


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pedrobaonza.spaceflight.data.repository.SpaceNewsRepository

class ArticlesViewModelFactory(
    private val repository: SpaceNewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArticlesViewModel(repository) as T
    }
}
