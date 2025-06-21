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
        val amount = 250.0
        val tipPercent = 15
        val roundUp = false
        val numberOfPeople = 5

        val expectedResult = 57.5

        val tip = calculateTip(amount, tipPercent, roundUp)

        val totalPerPerson = (amount + tip) / numberOfPeople

        assertEquals(expectedResult, totalPerPerson, 0.0)
    }

    //Pruebas adicionales

    @Test
    fun calculateWithZeroPercent() {
        val amount = 150.0
        val tipPercent = 0
        val roundUp = false

        val expected = 0.0

        val result = calculateTip(amount, tipPercent, roundUp)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun calculateMinimumTipWithRoundUp() {
        val amount = 1.99
        val tipPercent = 10
        val roundUp = true

        val expected = 1.0

        val result = calculateTip(amount, tipPercent, roundUp)

        assertEquals(expected, result, 0.0)
    }
}
