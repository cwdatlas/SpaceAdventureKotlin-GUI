package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.models.ButtonActions
import data.models.Rocket
import utils.handCursor

@Composable
fun Title(infoPanel: (selectedButton: ButtonActions) -> Unit, rocket:Rocket, prevDeltaV:Int, numLaunches:Int) {

    Column(
        modifier = Modifier.fillMaxWidth(0.25f).fillMaxHeight().padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Rocket Builder", Modifier.padding(1.dp))

        // Generate all buttons stated in the ActionButtons
        ButtonActions.entries.forEach {
            TextButton(
                onClick = {
                    infoPanel(it)
                }
            ) {
                Text(
                    it.toString(),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.pointerHoverIcon(handCursor())
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
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
            "Launch Attempts: $numLaunches",
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