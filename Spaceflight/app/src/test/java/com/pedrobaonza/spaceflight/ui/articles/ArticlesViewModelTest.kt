package com.pedrobaonza.spaceflight.ui.articles

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.pedrobaonza.spaceflight.data.model.Article
import com.pedrobaonza.spaceflight.data.model.ArticlesResponse
import com.pedrobaonza.spaceflight.data.repository.SpaceNewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class ArticlesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: SpaceNewsRepository
    private lateinit var viewModel: ArticlesViewModel

    @Before
    fun setUp() {
        repository = mock(SpaceNewsRepository::class.java)
        viewModel = ArticlesViewModel(repository)
    }

    @Test
    fun fetchArticlesUpdatesArticlesLiveDataWhenSuccessful() = runTest {
        // Arrange
        val fakeArticles = listOf(
            Article(1, "Title 1", "url1", "image1", "summary1", "2024-01-01T00:00:00Z")
        )
        val fakeResponse = Response.success(
            ArticlesResponse(1, null, null, fakeArticles)
        )
        `when`(repository.getArticles(10, 0)).thenReturn(fakeResponse)

        // Act
        viewModel.fetchArticles()

        // Assert
        val result = viewModel.articles.getOrAwaitValue()
        assertEquals(fakeArticles, result)
    }

    @Test
    fun fetchArticlesUpdatesErrorLiveDataWhenApiFails() = runTest {
        // Arrange
        `when`(repository.getArticles(10, 0)).thenReturn(
            Response.error(500, okhttp3.ResponseBody.create(null, "Server Error"))
        )

        // Act
        viewModel.fetchArticles()

        // Assert
        val errorMessage = viewModel.error.getOrAwaitValue()
        assertNotNull(errorMessage)
        assertTrue(errorMessage!!.contains("Error"))
    }

    /**
     * Utilidad para esperar el valor de un LiveData en tests con corrutinas.
     */
    private fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)

        val observer = object : Observer<T> {
            override fun onChanged(t: T) {
                data = t
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }

        this.observeForever(observer)

        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}
