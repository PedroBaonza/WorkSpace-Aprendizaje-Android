package com.pedrobaonza.fundamentosjunit

import org.junit.After
import org.junit.AfterClass
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class ParameterizedTest(private var currentValue: Boolean, private var currentUser: User) {

    @get:Rule
    val locationESRule = LocationESRule()

    companion object {

        var assertions : Assertions? = null

        @BeforeClass
        @JvmStatic
        fun setupCommon() {
            assertions = Assertions()
        }

        @AfterClass
        @JvmStatic
        fun tearDownCommon() {
            assertions = null
        }

//        @Parameterized.Parameters @JvmStatic
////        fun getUsersUS() = arrayOf(
////            arrayOf(false, User("Pedro", 12)),
////            arrayOf(true, User("Clara", 34)),
////            arrayOf(true, User("Bot21", 4, false)),
////            arrayOf(false, User("Clara", 18))
////        )

        @Parameterized.Parameters @JvmStatic
        fun getUsersES() = arrayOf(
            arrayOf(true, User("Pedro", 12)),
            arrayOf(false, User("Clara", 34)),
            arrayOf(true, User("Bot21", 4, false)),
            arrayOf(true, User("Clara", 18))
        )

    }

    @Test
    fun isAdultTest() {

        //assertEquals(currentValue, assertions?.isAdult(currentUser))
        assertEquals(currentValue, locationESRule.assertions?.isAdult(currentUser))

    }

}