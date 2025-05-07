package com.finalretrofittest.testingappdemo.view.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finalretrofittest.testingappdemo.R
import com.finalretrofittest.testingappdemo.data.api.RetrofitInstance
import com.finalretrofittest.testingappdemo.data.repository.PostRepository
import com.finalretrofittest.testingappdemo.domain.usecase.GetPostsUseCase
import com.finalretrofittest.testingappdemo.view.viewmodel.PostUiState
import com.finalretrofittest.testingappdemo.view.viewmodel.PostViewModel
import com.finalretrofittest.testingappdemo.view.viewmodel.PostViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val viewModel: PostViewModel by viewModels { PostViewModelFactory() }
    private val adapter = PostAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.fetchPosts()

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is PostUiState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is PostUiState.Success -> {
                        progressBar.visibility = View.GONE
                        adapter.updateData(state.posts)
                    }
                    is PostUiState.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, state.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}