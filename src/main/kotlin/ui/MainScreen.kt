package ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import data.models.ButtonActions

@Composable
fun MainScreen(){
    var pressedButton by remember{ mutableStateOf(ButtonActions.CAPSULE)}


    Row {
        // Title
        Title(infoPanel = {
            pressedButton = it})
        // Info
        Info(pressedButton)
    }

}