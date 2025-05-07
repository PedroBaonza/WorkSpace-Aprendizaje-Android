package com.pedrobaonza.fundamentosjunit

import org.junit.After
import org.junit.AfterClass
import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class AssertionsUsersTest {

    private lateinit var robot: User

    companion object {

        private lateinit var pedro: User

        @JvmStatic
        @BeforeClass
        fun setupCommon(): Unit {
            pedro = User("Pedro", 21, true)
            println("BeforeClass")
        }

        @JvmStatic
        @AfterClass
        fun tearDownCommon(): Unit {
            pedro = User()
            println("AfterClass")
        }
    }

    @Before
    fun setup() {
        robot = User("8Bit", 1, false)
        println("Before")
    }

    @After
    fun tearDown() {
        robot = User()
        println("After")
    }

    @Test
    fun checkHumanTest() {
        val assertions = Assertions()
        assertFalse(assertions.checkHuman(robot))
        assertTrue(assertions.checkHuman(pedro))
        println("checkHumanTest")
    }

    @Test
    fun checkNotNullUserTest() {
        assertNotNull(pedro)
        println("checkNotNullUserTest")
    }

    @Test
    fun checkNotSameUsersTest() {
        assertNotSame(pedro, robot)
        println("checkNotSameUsersTest")
    }

    @Test
    fun checkSameUsersTest() {
        val pedroCopy = pedro
        assertSame(pedroCopy, pedro)
        println("checkSameUsersTest")
    }


}