package com.pedrobaonza.spaceflight.data.repository

import com.pedrobaonza.spaceflight.data.api.SpaceNewsApiService
import com.pedrobaonza.spaceflight.data.model.Article
import com.pedrobaonza.spaceflight.data.model.ArticlesResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response

/**
 * Clase de tests unitarios para SpaceNewsRepository.
 *
 * Objetivo:
 * Verificar que SpaceNewsRepository maneja correctamente las respuestas de la API
 * tanto en casos exitosos como en casos de error, simulando distintos escenarios de red.
 */
@ExperimentalCoroutinesApi
class SpaceNewsRepositoryTest {

    // Variables necesarias para los tests
    private lateinit var repository: SpaceNewsRepository
    private lateinit var apiService: SpaceNewsApiService

    /**
     * Este metodo se ejecuta automáticamente antes de cada test.
     *
     * Preparación:
     * - Crea un mock (simulación) de SpaceNewsApiService usando Mockito.
     * - Crea una instancia de SpaceNewsRepository utilizando el mock.
     *
     * Así garantizamos que cada test empieza en un estado limpio y controlado.
     */
    @Before
    fun setUp() {
        apiService = mock(SpaceNewsApiService::class.java)
        repository = SpaceNewsRepository(apiService)
    }

    /**
     * Test 1: Verifica que el repositorio devuelve correctamente una lista de artículos
     * cuando la API responde exitosamente (HTTP 200 OK).
     *
     * Patrón AAA:
     * - Arrange: Preparamos datos falsos y configuramos el mock.
     * - Act: Llamamos a getArticles().
     * - Assert: Verificamos que la respuesta fue exitosa y contiene los artículos esperados.
     */
    @Test
    fun getArticlesReturnsSuccessWhenApiCallIsSuccessful() = runTest {
        // Arrange
        val fakeArticles = listOf(
            Article(1, "Title 1", "https://url1.com", "https://image1.com", "summary1", "2024-01-01T00:00:00Z"),
            Article(2, "Title 2", "https://url2.com", "https://image2.com", "summary2", "2024-01-02T00:00:00Z")
        )
        val fakeResponse = ArticlesResponse(
            count = 2,
            next = null,
            previous = null,
            results = fakeArticles
        )
        `when`(apiService.getArticles(10, 0)).thenReturn(Response.success(fakeResponse))

        // Act
        val response = repository.getArticles()

        // Assert
        assertTrue(response.isSuccessful)
        assertNotNull(response.body())
        assertEquals(2, response.body()?.results?.size)
    }

    /**
     * Test 2: Verifica que el repositorio detecta y maneja correctamente un error HTTP,
     * en este caso un 404 (Not Found).
     *
     * Patrón AAA:
     * - Arrange: Simulamos una respuesta de error desde el mock.
     * - Act: Llamamos a getArticles().
     * - Assert: Verificamos que la respuesta no fue exitosa y que el código es 404.
     */
    @Test
    fun getArticlesReturnsErrorWhenApiCallFails() = runTest {
        // Arrange
        `when`(apiService.getArticles(10, 0)).thenReturn(
            Response.error(404, okhttp3.ResponseBody.create(null, "Not Found"))
        )

        // Act
        val response = repository.getArticles()

        // Assert
        assertFalse(response.isSuccessful)
        assertEquals(404, response.code())
    }

    /**
     * Test 3: Verifica que el repositorio maneja correctamente una respuesta exitosa
     * que contiene una lista vacía de artículos.
     *
     * Patrón AAA:
     * - Arrange: Configuramos el mock para devolver una respuesta vacía.
     * - Act: Llamamos a getArticles().
     * - Assert: Verificamos que la lista de resultados está vacía.
     */
    @Test
    fun getArticlesReturnsEmptyListWhenNoArticles() = runTest {
        // Arrange
        val fakeResponse = ArticlesResponse(
            count = 0,
            next = null,
            previous = null,
            results = emptyList()
        )
        `when`(apiService.getArticles(10, 0)).thenReturn(Response.success(fakeResponse))

        // Act
        val response = repository.getArticles()

        // Assert
        assertTrue(response.isSuccessful)
        assertNotNull(response.body())
        assertEquals(0, response.body()?.results?.size)
    }

    /**
     * Test 4: Verifica que el repositorio maneja correctamente una excepción lanzada
     * por la API (por ejemplo, error de red, timeout, etc).
     *
     * Este test simula un escenario donde Retrofit lanza una excepción en lugar de devolver Response.
     */
    @Test(expected = Exception::class)
    fun getArticlesThrowsExceptionWhenApiFailsCompletely() = runTest {
        // Arrange
        `when`(apiService.getArticles(10, 0)).thenThrow(RuntimeException("Fallo en la red"))

        // Act
        repository.getArticles() // Debería lanzar una excepción

        // Assert
        /* El propio @Test(expected = Exception::class) validará si se lanza la excepción */
    }

}
