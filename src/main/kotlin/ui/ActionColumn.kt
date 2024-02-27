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
import data.models.AppState
import data.models.ButtonActions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import utils.handCursor

@Composable
fun Title(infoPanel: (selectedButton: ButtonActions) -> Unit) {

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
            if (AppState.rocket.getTWR().isNaN()) "TWR: 0" else "TWR: ${AppState.rocket.getTWR()}",
            color = Color.Black,
            fontWeight = FontWeight.Light
        )
        Text(
            "ISP: ${AppState.rocket.getISP()}",
            color = Color.Black,
            fontWeight = FontWeight.Light
        )
        Text(
            "Engine Number: ${AppState.rocket.engineNumber}",
            color = Color.Black,
            fontWeight = FontWeight.Light
        )
        Text(
            "Launch Attempts: ${AppState.numberOfLaunches}",
            color = Color.Black,
            fontWeight = FontWeight.Light
        )
        Text(
            "Previous DeltaV: ${AppState.previousDeltaV}",
            color = Color.Black,
            fontWeight = FontWeight.Light
        )
    }
}