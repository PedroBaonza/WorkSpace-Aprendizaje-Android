package com.pedrobaonza.spaceflight.ui.articles


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrobaonza.spaceflight.data.model.Article
import com.pedrobaonza.spaceflight.data.repository.SpaceNewsRepository
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val repository: SpaceNewsRepository
) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchArticles(limit: Int = 10, offset: Int = 0) {
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val response = repository.getArticles(limit, offset)
                if (response.isSuccessful) {
                    response.body()?.let { articlesResponse ->
                        _articles.value = articlesResponse.results
                    }
                } else {
                    _error.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
