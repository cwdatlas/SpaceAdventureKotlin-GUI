package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.models.ButtonActions
import data.models.Rocket
import utils.handCursor

/**
 * @author Aidan Scott
 * The ActionColumn function provides the gui so users can switch to a new view by the ViewColumn function by clicking buttons.
 *
 * ActionColumn is heavily contributed to by the tutorial by YoursSohail mentioned in the README.
 * Needed code was copied during the tutorial, then made my own to fit my purpose.
 * ~25% of the code was made by YoursSohail with small changes, the rest was made with large changes, or in the same structure.
 *
 * All comments are mine, this is the case throughout the program.
 */
@Composable
fun ActionColumn(infoPanel: (selectedButton: ButtonActions) -> Unit, rocket: Rocket, prevDeltaV: Int) {

    // the first column to format everything into, this function (ActionColumn) works similarly to thymleaf's fragment system so it can be added on anywhere
    Column(
        // formatting column, the code is quite human-readable.
        // color, modifier and other similar aesthetic design is placed in the constructor of the object
        modifier = Modifier.fillMaxWidth(0.25f).fillMaxHeight().padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Other objects like Text are placed in the parent object's function
        Text(
            "Rocket Builder",
            Modifier.padding(1.dp),
            fontWeight = FontWeight.ExtraBold
        )

        // Generate all buttons stated in the ActionButtons, this provides flexible additions to buttons
        ButtonActions.entries.forEach {
            TextButton(
                onClick = {
                    // function that describes what happens when button is clicked.
                    // In this case the infoPanel function will be called setting it (the specific ButtonAction) to a variable in the MainScreen
                    infoPanel(it)
                }
            ) {
                Text(
                    it.toString(), // Enums are not strings so, they must be told to become a string
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.pointerHoverIcon(handCursor()) // Used to show the user is hovering over a clickable element
                )
            }
        }
        // All Statistics are generated and updated here
        Spacer(modifier = Modifier.padding(10.dp)) // A spacer, which puts space of amount 10.dp between previous section and statistics
        // All stats are printed out in a Light weight except for the title, printed out in Bold
        // TODO: make section more up to date, so it updates everything a user clicks a button, rather than just the launch button
        Text(
            "Rocket Statistics",
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Text(
            if (rocket.getTWR().isNaN()) "TWR: 0" else "TWR: ${rocket.getTWR()}",
            color = Color.Black,
            fontWeight = FontWeight.Light
        )
        Text(
            "ISP: ${rocket.getISP()}",
            color = Color.Black,
            fontWeight = FontWeight.Light
        )
        Text(
            "Engine Number: ${rocket.engineNumber}",
            color = Color.Black,
            fontWeight = FontWeight.Light
        )
        Text(
            "Previous DeltaV: $prevDeltaV",
            color = Color.Black,
            fontWeight = FontWeight.Light
        )
    }
}