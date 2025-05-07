package com.pedrobaonza.spaceflight.ui.articles

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pedrobaonza.spaceflight.R
import com.pedrobaonza.spaceflight.data.repository.SpaceNewsRepository
import com.pedrobaonza.spaceflight.utils.RetrofitInstance

class ArticlesActivity : ComponentActivity() {

    private lateinit var articlesAdapter: ArticlesAdapter
    private val viewModel: ArticlesViewModel by viewModels {
        ArticlesViewModelFactory(SpaceNewsRepository(RetrofitInstance.api))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        articlesAdapter = ArticlesAdapter()
        recyclerView.adapter = articlesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observadores
        viewModel.articles.observe(this) { articles ->
            articlesAdapter.submitList(articles)
        }

        viewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            // Aquí podrías mostrar un ProgressBar si quieres
        }

        // Pedimos los artículos
        viewModel.fetchArticles()
    }
}
