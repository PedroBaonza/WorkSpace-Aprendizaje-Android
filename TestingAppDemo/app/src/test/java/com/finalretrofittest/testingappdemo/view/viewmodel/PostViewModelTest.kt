package com.finalretrofittest.testingappdemo.view.viewmodel

import app.cash.turbine.test
import com.finalretrofittest.testingappdemo.domain.model.Post
import com.finalretrofittest.testingappdemo.domain.usecase.GetPostsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PostViewModelTest {

    private lateinit var useCase: GetPostsUseCase
    private lateinit var viewModel: PostViewModel

    /**
     * Se ejecuta antes de cada test.
     * Se mockea el caso de uso y se inicializa el ViewModel con dicho mock.
     */
    @Before
    fun setup() {
        useCase = mockk()
        viewModel = PostViewModel(useCase)
    }

    /**
     * AAA Test: fetchPosts_Exitoso_DeberiaEmitirLoadingYSuccess
     *
     * Arrange: Simulamos que el caso de uso devuelve una lista de posts.
     * Act: Se llama a fetchPosts().
     * Assert: uiState emite primero Loading, luego Success con los datos.
     */
    @Test
    fun givenSuccess_whenFetch_thenSuccess() = runTest {
        // Arrange
        val fakePosts = listOf(Post(1, 1, "Título 1", "Contenido 1"))
        coEvery { useCase() } returns fakePosts

        // Act + Assert usando Turbine
        viewModel.uiState.test {
            viewModel.fetchPosts()

            assertEquals(PostUiState.Loading, awaitItem()) // Emit Loading
            assertEquals(PostUiState.Success(fakePosts), awaitItem()) // Emit Success con datos

            cancelAndIgnoreRemainingEvents()
        }
    }

    /**
     * AAA Test: fetchPosts_Fallo_DeberiaEmitirLoadingYError
     *
     * Arrange: Simulamos que el caso de uso lanza una excepción.
     * Act: Se llama a fetchPosts().
     * Assert: uiState emite primero Loading, luego Error.
     */
    @Test
    fun givenError_whenFetch_thenError() = runTest {
        // Arrange
        coEvery { useCase() } throws Exception("Fallo de red")

        // Act + Assert
        viewModel.uiState.test {
            viewModel.fetchPosts()

            assertEquals(PostUiState.Loading, awaitItem()) // Emit Loading
            val errorItem = awaitItem()

            // Comprobamos que se emite un estado de error
            assertTrue(errorItem is PostUiState.Error)

            cancelAndIgnoreRemainingEvents()
        }
    }
}
