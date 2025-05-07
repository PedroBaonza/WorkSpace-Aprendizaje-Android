package com.finalretrofittest.testingappdemo.hamcrest

class AgeBasicValidator {

    fun isAdult(age: Int): Boolean {
        return age >= 18
    }

}