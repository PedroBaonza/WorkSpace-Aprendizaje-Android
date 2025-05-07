package com.finalretrofittest.testingappdemo.hamcrest

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

/**
 * Clase de pruebas avanzadas para LoginValidator utilizando Hamcrest.
 */
class LoginValidatorTest {

    private val validator = LoginValidator()

    @Test
    fun givenValidCredentials_whenValidated_thenReturnsSuccess() {
        val email = "user@example.com"
        val password = "securePassword"

        val result = validator.validate(email, password)

        // Verifica que el resultado es del tipo LoginResult.Success
        assertThat(result, instanceOf(LoginResult.Success::class.java))
    }

    @Test
    fun givenEmptyEmail_whenValidated_thenReturnsErrorWithSpecificMessage() {
        val email = ""
        val password = "securePassword"

        val result = validator.validate(email, password)

        // Verifica que el resultado es un LoginResult.Error...
        assertThat(result, instanceOf(LoginResult.Error::class.java))
        // ...y que contiene el mensaje exacto esperado
        val error = result as LoginResult.Error
        assertThat(error.message, equalTo("Email cannot be blank"))
    }

    @Test
    fun givenEmailWithoutAtSymbol_whenValidated_thenReturnsInvalidEmailError() {
        val email = "userexample.com"
        val password = "securePassword"

        val result = validator.validate(email, password)

        // `allOf()` permite validar varias condiciones a la vez
        // - que sea de tipo Error
        // - que su propiedad `message` contenga "@"
        assertThat(result, allOf(
            instanceOf(LoginResult.Error::class.java),
            hasProperty("message", containsString("@"))
        ))
    }

    @Test
    fun givenShortPassword_whenValidated_thenReturnsPasswordTooShortError() {
        // Arrange
        val email = "user@example.com"
        val password = "123"

        // Act
        val result = validator.validate(email, password)

        // `isA()` es similar a `instanceOf()` pero más idiomático en Kotlin
        // `startsWith()` valida el inicio del mensaje
        assertThat(result, allOf(
            isA(LoginResult.Error::class.java),
            hasProperty("message", startsWith("Password must be"))
        ))
    }

    @Test
    fun givenWhitespaceEmail_whenValidated_thenReturnsBlankEmailError() {
        val email = "     "
        val password = "securePassword"

        val result = validator.validate(email, password)

        // Verifica el valor exacto de la propiedad `message`
        assertThat(result, allOf(
            isA(LoginResult.Error::class.java),
            hasProperty("message", equalTo("Email cannot be blank"))
        ))
    }

    @Test
    fun givenBlankPassword_whenValidated_thenReturnsBlankPasswordError() {
        val email = "user@example.com"
        val password = ""

        val result = validator.validate(email, password)

        // Assert
        // `containsString()` permite validar parcialmente un mensaje
        assertThat(result, allOf(
            instanceOf(LoginResult.Error::class.java),
            hasProperty("message", containsString("Password cannot be"))
        ))
    }

    @Test
    fun givenMultipleInvalidInputs_whenValidated_thenReturnsFirstErrorOnly() {
        val email = ""
        val password = ""

        val result = validator.validate(email, password)

        // Aunque haya múltiples errores, solo se reporta el primero (email)
        assertThat(result, isA(LoginResult.Error::class.java))
        assertThat((result as LoginResult.Error).message, equalTo("Email cannot be blank"))
    }
}
