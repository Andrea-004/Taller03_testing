package com.example.testeableapp

import com.example.testeableapp.ui.Screens.calculateTip
import org.junit.Assert.assertEquals
import org.junit.Test

class TipLogicTest {

    @Test
    fun calculate37PercentageTipWithRoundUp() {
        val amount = 100.0
        val tipPercent = 37
        val roundUp = true

        val expectedTip = 37.0

        val result = calculateTip(amount, tipPercent, roundUp)

        assertEquals(expectedTip, result, 0.0)
    }

    @Test
    fun calculateNegativeTip() {
        val amount = -50.0
        val tipPercent = 20
        val roundUp = false

        val expectedTip = 0.0

        val result = calculateTip(amount, tipPercent, roundUp)

        assertEquals(expectedTip, result, 0.0)
    }

    @Test
    fun totalPerPersonWithTip() {
        val amount = 120.0
        val tipPercent = 10
        val roundUp = false
        val numberOfPeople = 4

        val expectedResult = 33.0

        val tip = calculateTip(amount, tipPercent, roundUp)

        val totalPerPerson = (amount + tip) / numberOfPeople

        assertEquals(expectedResult, totalPerPerson, 0.0)
    }
}
