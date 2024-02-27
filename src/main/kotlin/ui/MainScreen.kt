package ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import data.models.AppState
import data.models.ButtonActions

/**
 * @author Aidan Scott
 * MainScreen() puts all other gui elements together into one contiguous screen and manages the sharing of resources between
 * all elements.
 *
 * MainScreen() is heavily influenced by the tutorial by YoursSohail mentioned in the README.
 * The structure of the function is fully provided by YoursSohail, which then I heavily edited to make it my own.
 */
@Composable
fun MainScreen() {
    // init variables from AppState
    // This method allows the function to remember the variables from one call to the next, cool
    var pressedButton by remember { mutableStateOf(ButtonActions.CAPSULE) }
    var rocket by remember { mutableStateOf(AppState.rocket) }
    var prevDeltaV by remember { mutableStateOf(0) }


    Row {
        // The ActionColumn displays buttons and title
        ActionColumn(
            infoPanel = {
                pressedButton = it // once infoPanel is called with a Button Action for the arg, it will update the local ButtonAction
            },
            rocket, prevDeltaV // To function ActionColumn needs a rocket and previous DeltaV data to display statistics
        )
        // The ViewColumn displays results from ActionButton presses
        ViewColumn(pressedButton,
            rocketD = { rocket = it }, // update rocket when it has been 'returned
            prevDeltaVD = { prevDeltaV = it }) // update previous DelaV when it has been 'returned'
    }

}