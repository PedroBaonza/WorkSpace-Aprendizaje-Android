package com.pedrobaonza.fundamentosjunit

import org.junit.Assert.*
import org.junit.Test
import kotlin.random.Random

/**
 * Clase de pruebas unitarias con JUnit 4.
 * Aquí se testean los métodos de la clase Assertions usando diferentes tipos de aserciones.
 */
class AssertionsTest {

    /**
     * Test que verifica que el array de números devuelto es el esperado.
     * Se usa assertArrayEquals para comparar arrays.
     */
    @Test
    fun getArrayTest() {
        val assertions = Assertions()
        val expectedArray = arrayOf(21, 117)
        assertArrayEquals(expectedArray, assertions.getLuckyNumbers())
    }

    /**
     * Test para verificar que el nombre devuelto es "Pedro".
     * También comprueba que no sea un nombre incorrecto como "Max".
     */
    @Test
    fun getNameTest() {
        val assertions = Assertions()
        assertEquals("Pedro", assertions.getName())        // Valor esperado correcto
        assertNotEquals("Max", assertions.getName())       // Valor incorrecto
    }

    /**
     * Verifica si el método checkHuman funciona correctamente.
     * - Un humano devuelve true
     * - Un robot devuelve false
     */
    @Test
    fun checkHumanTest() {
        val assertions = Assertions()
        val pedro = User("Pedro", 21, true)
        val robot = User("8Bit", 1, false)
        assertTrue(assertions.checkHuman(pedro))
        assertFalse(assertions.checkHuman(robot))
    }

    /**
     * Verifica que si el usuario es null, el método checkHuman devuelva null.
     */
    @Test
    fun checkNullUserTest() {
        val assertions = Assertions()
        val user = null
        assertNull(user)                             // Se asegura que es null
        assertNull(assertions.checkHuman(user))      // El metodo debe devolver null
    }

    /**
     * Comprueba que un objeto no sea null.
     * Sirve para practicar assertNotNull.
     */
    @Test
    fun checkNotNullUserTest() {
        val pedro = User("Pedro", 21, true)
        assertNotNull(pedro)
    }

    /**
     * Compara que dos objetos distintos no sean la misma instancia en memoria.
     */
    @Test
    fun checkNotSameUsersTest() {
        val pedro = User("Pedro", 21, true)
        val juan = User("Juan", 31, true)
        assertNotSame(pedro, juan)
    }

    /**
     * Comprueba que dos referencias apuntan al mismo objeto exacto.
     */
    @Test
    fun checkSameUsersTest() {
        val pedro = User("Pedro", 21, true)
        assertSame(pedro, pedro)
    }

    /**
     * Test con límite de tiempo (en JUnit 4).
     * Este tipo de test sirve para comprobar rendimiento o detectar bloqueos.
     */
    @Test(timeout = 1000)
    fun getCitiesTest() {
        val cities = arrayOf("México", "Canadá", "EEUU")
        Thread.sleep(Random.nextLong(250, 1100))  // Puede hacer que falle si se pasa del tiempo
        assertEquals(3, cities.size)
    }
}
