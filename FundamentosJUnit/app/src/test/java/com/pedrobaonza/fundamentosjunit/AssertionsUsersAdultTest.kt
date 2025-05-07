package com.pedrobaonza.fundamentosjunit

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class AssertionsUsersAdultTest {

    private lateinit var robot: User
    private lateinit var pedro: User

    @get:Rule
    val locationESRule = LocationESRule()

    @Before
    fun setup() {
        robot = User("8Bit", 18, false)
        pedro = User("Pedro", 22, true)
        println("Before")
    }

    @After
    fun tearDown() {
        robot = User()
        pedro = User()
        println("After")
    }

    @Test
    fun isAdultTest() {
//        val assertions = Assertions()
//        assertions.setLocation("ES")
//        assertTrue(assertions.isAdult(pedro))
//        assertTrue(assertions.isAdult(robot))
//        println("isAdultTest")

        assertEquals(true, locationESRule.assertions?.isAdult(pedro))
        assertEquals(true, locationESRule.assertions?.isAdult(robot))
        println("isAdultTest")

    }
}