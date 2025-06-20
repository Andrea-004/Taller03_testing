package com.example.testeableapp

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.testeableapp.ui.Screens.TipCalculatorScreen
import org.junit.Rule
import org.junit.Test

class TipLogicUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun tipValueChangesWhenRoundingIsEnabled() {
        composeRule.setContent {
            TipCalculatorScreen()
        }

        //para que funcione este test tuve que agregar testTags en el codigo para poder acceder a los componentes jptape. documentas esto

        val bill = "105.10"

        val expectedUnroundedTip = "Propina: $15.77"

        val expectedRoundedTip = "Propina: $16.00"

        composeRule.onNodeWithTag("billAmount").performTextInput(bill)

        composeRule.onNodeWithText(expectedUnroundedTip).assertExists()

        composeRule.onNodeWithTag("roundCheckbox").performClick()

        composeRule.onNodeWithText(expectedRoundedTip).assertExists()
    }

    @Test
    fun tipChangesWhenSliderMoves() {
        composeRule.setContent {
            TipCalculatorScreen()
        }

        //para que funcione este test tuve que agregar testTags en el codigo para poder acceder a los componentes jptape. documentas esto

        composeRule.onNodeWithTag("billAmount").performTextInput("100")

        val initialTip = composeRule.onNodeWithTag("tipText")
            .fetchSemanticsNode()
            .config[SemanticsProperties.Text]
            .first()
            .text

        composeRule.onNodeWithTag("tipSlider").performTouchInput {
            repeat(3) { swipeRight() }
        }

        val updatedTip = composeRule.onNodeWithTag("tipText")
            .fetchSemanticsNode()
            .config[SemanticsProperties.Text]
            .first()
            .text

        assert(initialTip != updatedTip) {
            "La propina no cambio porque la regue en algo. Propina antes: $initialTip, Propina despues: $updatedTip"
        }
    }

    @Test
    fun verifyUiElementsAreDisplayed() {
        composeRule.setContent {
            TipCalculatorScreen()
        }

        //para que funcione este test tuve que agregar testTags en el codigo para poder acceder a los componentes jptape. documentas esto

        composeRule.onNodeWithTag("billAmount").assertExists()
        composeRule.onNodeWithTag("tipPercentageText").assertExists()
        composeRule.onNodeWithTag("tipSlider").assertExists()
        composeRule.onNodeWithTag("numberPeopleText").assertExists()
        composeRule.onNodeWithTag("roundCheckbox").assertExists()
        composeRule.onNodeWithTag("tipText").assertExists()
        composeRule.onNodeWithTag("totalPerPerson").assertExists()
    }

    //pruebas de UI adicionales

    @Test
    fun numberOfPeopleDoesNotGoBelowOne() {
        composeRule.setContent {
            TipCalculatorScreen()
        }

        //para que funcione este test tuve que agregar testTags en el codigo para poder acceder a los componentes jptape. documentas esto

        repeat(3) {
            composeRule.onNodeWithTag("decreasePeopleButton").performClick()
        }

        composeRule.onNodeWithText("Número de personas: 1").assertExists()
    }

    @Test
    fun totalPerPersonChangesWhenPeopleIncrease() {
        composeRule.setContent {
            TipCalculatorScreen()
        }

        //para que funcione este test tuve que agregar testTags en el codigo para poder acceder a los componentes jptape. documentas esto

        composeRule.onNodeWithTag("billAmount").performTextInput("100")

        val initialTotal = composeRule.onNodeWithTag("totalPerPerson")
            .fetchSemanticsNode().config[SemanticsProperties.Text]
            .first()
            .text

        composeRule.onNodeWithTag("increasePeopleButton").performClick()

        val updatedTotal = composeRule.onNodeWithTag("totalPerPerson")
            .fetchSemanticsNode().config[SemanticsProperties.Text]
            .first()
            .text

        assert(initialTotal != updatedTotal) {
            "La regue y el total por persona no cambio al aumentar el numero de personas. Antes: $initialTotal, Despues: $updatedTotal"
        }
    }

}
