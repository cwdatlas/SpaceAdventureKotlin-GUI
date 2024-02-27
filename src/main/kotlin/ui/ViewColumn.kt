package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.models.AppState
import data.models.ButtonActions
import data.InitParts
import data.models.Rocket
import utils.handCursor

/**
 * @author Aidan Scott
 * InfoPanel() visualizes all parts and provides an interface for selecting each part,
 * and for engines the number of parts.
 *
 * InfoPanel is heavily contributed to by the tutorial by YoursSohail mentioned in the README.
 * ~25% is directly copied from YoursSohail with minimal changes, with the rest being totally original or with similar design.
 * This percent is primarily due to the amount of duplicate code, that I put together, for deciding how the parts should be displayed.
 *
 * @param buttonPressed ButtonActions: is used so a specific view can be displayed
 * @param rocketD (rocket: Rocket): is used so the rest of the app can be updated regarding local changes to rocket
 * @param prevDeltaVD (prevDeltaV: Int): is used so the rest of the app can be updated regarding local changes to prevDeltaV
 */
@Composable
fun ViewColumn(buttonPressed: ButtonActions,
               rocketD: (rocket: Rocket) -> Unit,
               prevDeltaVD: (prevDeltaV: Int) -> Unit) {
    // Init all needed variables as they dont come with lambda args
    val rocket by remember{ mutableStateOf(AppState.rocket)}
    var infoLog by remember { mutableStateOf("Unlaunched") }

    // This should be a when, as this if statement is used to make many different decisions based on ButtonActions
    // Go through each launch option and give feedback for each one
    if (buttonPressed == ButtonActions.LAUNCH) {
        if (rocket.capsule.name == "Unset") {
            infoLog = "Please add a Capsule to your rocket then attempt a launch"
        } else if (rocket.tank.name == "Unset") {
            infoLog = "Please add a Tank to your rocket then attempt a launch"
        } else if (rocket.engine.name == "unset") {
            infoLog = "Please add an Engine to your rocket then attempt a launch"
        } else if (rocket.getTWR() < AppState.USEABLETWR) {
            infoLog = "Your Thrust to weight ratio of ${rocket.getTWR()} must be more than 1.5 to launch"
        } else if (rocket.getDeltaV() < AppState.TOORBITDELTAV) {
            infoLog = "You need to achieve ${AppState.TOORBITDELTAV}, you had ${rocket.getDeltaV()}."
            prevDeltaVD(rocket.getDeltaV())// update MainScreen's previous DeltaV number

        } else if (rocket.getDeltaV() >= AppState.TOORBITDELTAV){
            infoLog = "Good job! you made it to orbit with ${rocket.getDeltaV()} Delta V!! WOOHOO!"
            prevDeltaVD(rocket.getDeltaV())// update MainScreen's previous DeltaV number

        }
        // Visualize launch result text.
        // Optimally this would be a rocket launch video rather than boring text
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                infoLog,
                fontWeight = FontWeight.Bold
            )
        }
        // What should be done if buttonPressed is equal to ButtonActions.CAPSULE?
        // This function is VERY similar to the funcs for tanks and engines with some key differences which I will comment
    } else if (buttonPressed == ButtonActions.CAPSULE) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White).padding(8.dp)
        ) {
            // Creating title based on ButtonPressed
            Text(text = buttonPressed.name.lowercase(), fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.padding(4.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Starting a loop? that creates all parts that can be selected
                // Section of non-duplicate code
                items(InitParts.capsules.values.toMutableList()) {
                    Card(
                        modifier = Modifier.width(400.dp).height(200.dp).padding(4.dp).pointerHoverIcon(handCursor())
                            .clickable {
                                // set rocket part to it, then update it in MainScreen
                                rocket.capsule = it
                                rocketD(rocket)
                            }
                    ) {
                        // Start of visual section
                        Box {
                            // Select image based on part name
                            val bitmap = useResource("${it.name}.jpg".lowercase()) {
                                loadImageBitmap(it)
                            }
                            Image(
                                bitmap,
                                it.name,
                                modifier = Modifier.size(100.dp).align(Alignment.TopCenter),
                                contentScale = ContentScale.Fit
                            )
                            Column(
                                modifier = Modifier.align(Alignment.BottomStart)
                                    .background(Color.White).padding(4.dp)
                            ) {
                                Text(
                                    it.name,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                                // Display statistics of each part
                                Spacer(modifier = Modifier.padding(2.dp))
                                Text(
                                    "${it.mass} kg",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Light
                                )
                                Text(
                                    "${it.crew} Astronauts",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Light
                                )
                            }
                        }
                    }
                }
            }
        }
    } else if (buttonPressed == ButtonActions.TANK) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White).padding(8.dp)
        ) {
            // Creating title based on ButtonPressed
            Text(text = buttonPressed.name.lowercase(), fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.padding(4.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Starting a loop? that creates all parts that can be selected
                // Section of non-duplicate code
                items(InitParts.tanks.values.toMutableList()) {
                    Card(
                        modifier = Modifier.width(400.dp).height(200.dp).padding(4.dp).pointerHoverIcon(handCursor())
                            .clickable {
                                // set rocket part to it, then update it in MainScreen
                                rocket.tank = it
                                rocketD(rocket)
                            }
                    ) {
                        // Start of visual section
                        Box {
                            // Select image based on part name
                            val bitmap = useResource("${it.name}.jpg".lowercase()) {
                                loadImageBitmap(it)
                            }
                            Image(
                                bitmap,
                                it.name,
                                modifier = Modifier.size(100.dp).align(Alignment.TopCenter),
                                contentScale = ContentScale.Fit
                            )
                            Column(
                                modifier = Modifier.align(Alignment.BottomStart)
                                    .background(Color.White).padding(4.dp)
                            ) {
                                Text(
                                    it.name,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.padding(2.dp))
                                Text(
                                    "${it.mass} kg",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Light
                                )
                                // Display statistics of each part
                                Text(
                                    "${it.liquidFuel} kg",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Light
                                )
                                Text(
                                    "${it.width} meters",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Light
                                )
                            }
                        }
                    }
                }
            }
        }
    } else if (buttonPressed == ButtonActions.ENGINE) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White).padding(8.dp)
        ) {
            // Creating title based on ButtonPressed
            Text(text = buttonPressed.name.lowercase(), fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.padding(4.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Starting a loop? that creates all parts that can be selected
                // Section of non-duplicate code
                items(InitParts.engines.values.toMutableList()) {
                    var numEngines by remember { mutableStateOf(0) }
                    Card(
                        modifier = Modifier.width(400.dp).height(300.dp).padding(4.dp).pointerHoverIcon(handCursor())
                            .clickable {
                                // set rocket part to it, then update it in MainScreen
                                // Make sure user cant set engines to be 0
                                if (numEngines > 0) {
                                    rocket.engine = it
                                    rocket.engineNumber =
                                        // Logic that ensures too many engines wont be selected
                                        if (numEngines > rocket.getMaxEngines(it)) rocket.getMaxEngines(
                                            it
                                        ) else numEngines
                                    rocketD(rocket)
                                }
                            }
                    ) {
                        // Start of visual section
                        Box {
                            // Select image based on part name
                            val bitmap = useResource("${it.name}.jpg".lowercase()) {
                                loadImageBitmap(it)
                            }
                            Image(
                                bitmap,
                                it.name,
                                modifier = Modifier.size(100.dp).align(Alignment.TopCenter),
                                contentScale = ContentScale.Fit
                            )
                            Column(
                                modifier = Modifier.align(Alignment.BottomStart)
                                    .background(Color.White).padding(4.dp)
                            ) {
                                Text(
                                    it.name,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.padding(2.dp))
                                Text(
                                    "${it.mass} kg",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Light
                                )
                                // Display statistics of each part
                                Text(
                                    "${it.thrust} Kn",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Light
                                )
                                Text(
                                    "${it.isp} seconds",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Light
                                )
                                Text(
                                    "${it.width} meters",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Light
                                )
                                Text(
                                    // Display Text that that tank must be set if the user wishes to set an engine
                                    if (rocket.tank.name == "Unset") "Set Tank to add engine" else "$numEngines out of ${
                                        rocket.getMaxEngines(it)
                                    }",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                                // Totally from me section rather than copy paste update
                                // Creates a slider that will allow the user to select number of engines
                                Slider(
                                    value = numEngines.toFloat(),
                                    onValueChange = { numEngines = it.toInt() },
                                    valueRange = 0f..rocket.getMaxEngines(it).toFloat()
                                )
                            }
                        }
                    }
                }
            }
        }
        // Section for users to get help on how to use the game
    }else if (buttonPressed == ButtonActions.HELP){
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            // use line breaks to write more text with one String
            Text(
                "Welcome to Rocket Builder, your statistics will update when your attempt a launch!\n" +
                        "--------------------------------------Buttons--------------------------------------------\n" +
                        "    capsule -> Lists capsules and their stats. Any choice will override a previous choice\n" +
                        "    tank -> Lists tanks and their stats. Any choice will override a previous choice\n" +
                        "    engine -> Lists engines and their stats. Any choice will override a previous choice\n" +
                        "    launch -> Launches your rocket! See how far you get!\n" +
                        "    Help -> Provides help for the user.\n",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}