package com.finalretrofittest.testingappdemo.hamcrest

import org.junit.Assert.*
import org.junit.Test

/**
 * Pruebas unitarias básicas para AgeValidator usando JUnit.
 * Aprende:
 * - AAA (Arrange-Act-Assert)
 * - assertTrue / assertFalse
 * - Buenas prácticas de nombres Given-When-Then
 */
class AgeValidatorTest {

    private val validator = AgeBasicValidator()

    @Test
    fun givenAge18_whenChecked_thenReturnsTrue() {
        // Arrange
        val age = 18

        // Act
        val result = validator.isAdult(age)

        // Assert
        assertTrue(result)
    }

    @Test
    fun givenAge17_whenChecked_thenReturnsFalse() {
        // Arrange
        val age = 17

        // Act
        val result = validator.isAdult(age)

        // Assert
        assertFalse(result)
    }

    @Test
    fun givenAge0_whenChecked_thenReturnsFalse() {
        // Arrange
        val age = 0

        // Act
        val result = validator.isAdult(age)

        // Assert
        assertFalse(result)
    }

    @Test
    fun givenNegativeAge_whenChecked_thenReturnsFalse() {
        // Arrange
        val age = -5

        // Act
        val result = validator.isAdult(age)

        // Assert
        assertFalse(result)
    }
}
