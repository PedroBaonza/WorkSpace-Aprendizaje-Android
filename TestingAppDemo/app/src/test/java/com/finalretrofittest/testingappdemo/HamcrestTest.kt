package com.example.testingproject

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

/**
 * Clase de pruebas que demuestra el uso de Hamcrest en distintos escenarios.
 * Cada método sigue el patrón:
 * - Given: contexto o entrada
 * - When: acción a ejecutar
 * - Then: resultado esperado
 * También se aplica el patrón AAA (Arrange - Act - Assert).
 */
class HamcrestTest {

    @Test
    fun givenTwoNumbers_whenSummed_thenResultIsCorrect() {
        // Arrange
        val a = 5
        val b = 3

        // Act
        val result = a + b

        // Assert
        assertThat(result, `is`(equalTo(8)))
    }

    @Test
    fun givenAListOfFruits_whenCheckedForBanana_thenItIsContained() {
        // Arrange
        val fruits = listOf("apple", "banana", "orange")

        // Act & Assert
        assertThat(fruits, hasItem("banana"))
    }

    @Test
    fun givenAString_whenCheckedForPrefix_thenStartsWithHello() {
        // Arrange
        val message = "Hello, world!"

        // Act & Assert
        assertThat(message, startsWith("Hello"))
        assertThat(message, not(startsWith("Hi")))
    }

    @Test
    fun givenAList_whenCheckedForSize_thenReturnsExpectedCount() {
        // Arrange
        val numbers = listOf(1, 2, 3, 4, 5)

        // Act & Assert
        assertThat(numbers.size, `is`(5))
    }

    @Test
    fun givenTwoArrays_whenComparedIgnoringOrder_thenTheyAreEqual() {
        // Arrange
        val expected = arrayOf(1, 2, 3)
        val actual = arrayOf(3, 2, 1)

        // Act & Assert
        assertThat(actual.toList(), containsInAnyOrder(*expected))
    }

    @Test
    fun givenAMap_whenCheckedForEntry_thenContainsExpectedKeyValue() {
        // Arrange
        val map = mapOf("id" to 123, "name" to "Pedro")

        // Act & Assert
        assertThat(map, hasEntry("name", "Pedro"))
        assertThat(map, hasKey("id"))
        assertThat(map, hasValue(123))
    }

    @Test
    fun givenANumber_whenCheckedForNullAndRange_thenIsValid() {
        // Arrange
        val number: Int? = 10

        // Act & Assert
        assertThat(number, notNullValue())
        assertThat(number!!, allOf(greaterThan(5), lessThanOrEqualTo(10)))
    }

    @Test
    fun givenAString_whenCheckedAgainstMultipleConditions_thenAllMatch() {
        // Arrange
        val title = "Kotlin Testing Guide"

        // Act & Assert
        assertThat(title, allOf(
            containsString("Kotlin"),
            endsWith("Guide"),
            not(containsString("Java"))
        ))
    }

    @Test
    fun givenAnEmptyList_whenChecked_thenIsRecognizedAsEmpty() {
        // Arrange
        val emptyList = emptyList<String>()

        // Act & Assert
        assertThat(emptyList, empty())
        assertThat(emptyList, not(hasItem("anything")))
    }

    @Test
    fun givenBooleans_whenCheckedForValues_thenTheyMatchExpectedLogic() {
        // Arrange
        val isLoggedIn = true
        val hasPremiumAccess = false

        // Act & Assert
        assertThat(isLoggedIn, `is`(true))
        assertThat(hasPremiumAccess, not(`is`(true)))
    }
}
