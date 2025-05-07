package com.pedrobaonza.fundamentosjunit

/**
 * Clase de utilidades para realizar comprobaciones y devolver datos relacionados con usuarios.
 * Está pensada para practicar y aprender a realizar pruebas unitarias con JUnit 5.
 *
 * Contiene ejemplos de métodos que devuelven valores simples, trabajan con arrays,
 * validan condiciones booleanas y manejan valores nulos, todos ellos pensados para ser fácilmente testeables.
 */
class Assertions {

    // Instancia de un usuario por defecto para pruebas básicas
    private val user = User("Pedro", 21)
    private var location = "US"

    fun setLocation(location: String) {
        this.location = location
    }

    /**
     * Devuelve un array de números enteros considerados como "números de la suerte".
     *
     * @return Array de enteros con valores fijos.
     */
    fun getLuckyNumbers(): Array<Int> {
        return arrayOf(21, 117)
    }

    /**
     * Devuelve el nombre del usuario por defecto.
     *
     * @return Nombre del usuario (en este caso "Pedro").
     */
    fun getName(): String {
        return user.name
    }

    /**
     * Comprueba si el usuario proporcionado es humano.
     *
     * Este método está diseñado para ser probado con aserciones booleanas simples.
     *
     * @param user Usuario no nulo.
     * @return `true` si es humano, `false` si no lo es.
     */
    fun checkHuman(user: User): Boolean {
        return user.isHuman
    }

    /**
     * Variante del método anterior que acepta un usuario nulo.
     *
     * Sirve para practicar pruebas que involucran manejo de nulos y valores opcionales.
     *
     * @param user Usuario opcional (puede ser `null`).
     * @return `true` si es humano, `false` si no lo es, `null` si el usuario es `null`.
     */
    fun checkHuman(user: User?): Boolean? {
        if (user == null) return null
        return user.isHuman
    }

    /**
     * Determina si un usuario es considerado adulto.
     *
     * Si el usuario no es humano (por ejemplo, un robot), se considera automáticamente adulto.
     * Este método permite probar lógica condicional y ramas alternativas.
     *
     * @param user Usuario a comprobar.
     * @return `true` si es mayor de edad (>=18) o no es humano, `false` si es menor de edad y humano.
     */
    fun isAdult(user: User): Boolean {
        return if (!user.isHuman) true  // Si no es humano, es adulto automáticamente
        else if (location == "US") user.age >= 21
        else user.age >= 18
    }

}
