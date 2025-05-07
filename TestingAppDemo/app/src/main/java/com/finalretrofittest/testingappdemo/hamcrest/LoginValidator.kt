package com.finalretrofittest.testingappdemo.hamcrest

/**
 * Valida credenciales de login.
 * En lugar de devolver solo un booleano, se devuelve un LoginResult detallado,
 * lo que permite tests más ricos y específicos.
 */
class LoginValidator {

    fun validate(email: String, password: String): LoginResult {
        if (email.isBlank()) return LoginResult.Error("Email cannot be blank")
        if (!email.contains("@")) return LoginResult.Error("Email must contain @")
        if (password.isBlank()) return LoginResult.Error("Password cannot be blank")
        if (password.length < 6) return LoginResult.Error("Password must be at least 6 characters")

        return LoginResult.Success
    }
}

/**
 * Representa el resultado de una validación de login.
 */
sealed class LoginResult {
    data object Success : LoginResult()
    data class Error(val message: String) : LoginResult()
}