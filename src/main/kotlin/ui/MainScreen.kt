package ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import data.models.AppState
import data.models.ButtonActions

@Composable
fun MainScreen(){
    var pressedButton by remember{ mutableStateOf(ButtonActions.CAPSULE)}
    var rocket by remember{ mutableStateOf(AppState.rocket)}
    var prevDeltaV by remember{ mutableStateOf(0)}


    Row {
        // Title
        Title(infoPanel = {
            pressedButton = it},
            rocket, prevDeltaV)
        // Info
        Info(pressedButton,
            rocketD = { rocket = it },
            prevDeltaVD = {prevDeltaV = it})
    }

}