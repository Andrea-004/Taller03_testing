import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
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

        composeRule.onNodeWithText("Monto de la cuenta").performTextInput("102")

        val tipBefore = composeRule
            .onAllNodesWithText("Propina: $", substring = true)
            .onFirst()
            .fetchSemanticsNode()
            .config.getOrNull(SemanticsProperties.Text)
            ?.firstOrNull()
            ?.text

        composeRule.onNodeWithText("Redondear propina").performClick()

        val tipAfter = composeRule
            .onAllNodesWithText("Propina: $", substring = true)
            .onFirst()
            .fetchSemanticsNode()
            .config.getOrNull(SemanticsProperties.Text)
            ?.firstOrNull()
            ?.text

        assert(tipBefore != null && tipAfter != null) { "No se pudieron leer los valores de propina." }
        assert(tipBefore != tipAfter) { "La propina no cambió al activar el redondeo. Antes: $tipBefore, Después: $tipAfter" }
    }

}
