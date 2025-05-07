package com.finalretrofittest.testingappdemo.data.repository

import com.finalretrofittest.testingappdemo.data.api.ApiService
import com.finalretrofittest.testingappdemo.domain.model.Post
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * Clase de test para el repositorio PostRepository.
 * Utiliza MockWebServer para simular respuestas HTTP del servidor sin necesidad de una API real.
 *
 * Se cubren los siguientes escenarios:
 * - Respuesta 200 con JSON válido → debe parsearse correctamente.
 * - Respuesta 404 → debe lanzar una excepción.
 */
class PostRepositoryTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: ApiService
    private lateinit var repository: PostRepository

    /**
     * Configura el entorno de test antes de cada prueba:
     * - Inicia MockWebServer.
     * - Crea instancia de Retrofit apuntando a MockWebServer.
     * - Crea instancia del repositorio usando la API simulada.
     */
    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        repository = PostRepository(api)
    }

    /**
     * Apaga el servidor simulado después de cada test.
     */
    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    /**
     * AAA Test: getPosts_Respuesta200_DeberiaParsearListaCorrectamente
     *
     * Arrange: Se simula una respuesta HTTP 200 con JSON válido.
     * Act: Se llama al método getPosts() del repositorio.
     * Assert: Se comprueba que la lista tenga un solo elemento y que el título coincida.
     */
    @Test
    fun given200_whenGetPosts_thenReturnList() = runBlocking {
        // Arrange
        val fakePosts = listOf(Post(1, 1, "Título 1", "Contenido 1"))
        val json = Gson().toJson(fakePosts)
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(json))

        // Act
        val result = repository.getPosts()

        // Assert
        assertEquals(1, result.size)
        assertEquals("Título 1", result.first().title)
    }

    /**
     * AAA Test: getPosts_Respuesta404_DeberiaLanzarExcepcion
     *
     * Arrange: Se simula una respuesta HTTP 404.
     * Act: Se llama al método getPosts().
     * Assert: Se espera una excepción IOException debido a la respuesta fallida.
     */
    @Test(expected = IOException::class)
    fun given404_whenGetPosts_thenThrowException(): Unit = runBlocking {
        // Arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(404))

        // Act
        repository.getPosts()

        // Assert: Excepción esperada, no se necesita más validación aquí
    }
}
