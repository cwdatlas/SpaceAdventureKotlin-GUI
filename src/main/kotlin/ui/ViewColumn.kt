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
import data.models.InitParts
import utils.handCursor


@Composable
fun Info(buttonPressed: ButtonActions) {
    var infoLog by remember { mutableStateOf("Unlaunched") }

    if (buttonPressed == ButtonActions.LAUNCH) {
        if (AppState.rocket.capsule.name == "Unset") {
            infoLog = "Please add a Capsule to your rocket then attempt a launch"
        } else if (AppState.rocket.tank.name == "Unset") {
            infoLog = "Please add a Tank to your rocket then attempt a launch"
        } else if (AppState.rocket.engine.name == "unset") {
            infoLog = "Please add an Engine to your rocket then attempt a launch"
        } else if (AppState.rocket.getTWR() < AppState.useableTWR) {
            infoLog = "Your Thrust to weight ratio of ${AppState.rocket.getTWR()} must be more than 1.5 to launch"
        } else if (AppState.rocket.getDeltaV() < AppState.toOrbitDeltaV) {
            infoLog = "You need to achieve ${AppState.toOrbitDeltaV}, you had ${AppState.rocket.getDeltaV()}."
        } else {
            infoLog = "Good job! you made it to orbit with ${AppState.rocket.getDeltaV()} Delta V!! WOOHOO!"
            AppState.previousDeltaV = AppState.rocket.getDeltaV()
        }

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
    } else if (buttonPressed == ButtonActions.CAPSULE) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White).padding(8.dp)
        ) {
            Text(text = buttonPressed.name.lowercase(), fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.padding(4.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(InitParts.capsules.values.toMutableList()) {
                    Card(
                        modifier = Modifier.width(400.dp).height(200.dp).padding(4.dp).pointerHoverIcon(handCursor())
                            .clickable {
                                AppState.rocket.capsule = it
                            }
                    ) {
                        Box {
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
            Text(text = buttonPressed.name.lowercase(), fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.padding(4.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(InitParts.tanks.values.toMutableList()) {
                    Card(
                        modifier = Modifier.width(400.dp).height(200.dp).padding(4.dp).pointerHoverIcon(handCursor())
                            .clickable {
                                AppState.rocket.tank = it
                            }
                    ) {
                        Box {
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
            Text(text = buttonPressed.name.lowercase(), fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.padding(4.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(InitParts.engines.values.toMutableList()) {
                    var numEngines by remember { mutableStateOf(0) }
                    Card(
                        modifier = Modifier.width(400.dp).height(300.dp).padding(4.dp).pointerHoverIcon(handCursor())
                            .clickable {
                                if (numEngines > 0) {
                                    AppState.rocket.engine = it
                                    AppState.rocket.engineNumber =
                                        if (numEngines > AppState.rocket.getMaxEngines(it)) AppState.rocket.getMaxEngines(
                                            it
                                        ) else numEngines
                                }
                            }
                    ) {
                        Box {
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
                                    if (AppState.rocket.tank.name == "Unset") "Set Tank to add engine" else "$numEngines out of ${
                                        AppState.rocket.getMaxEngines(
                                            it
                                        )
                                    }",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                                Slider(
                                    value = numEngines.toFloat(),
                                    onValueChange = { numEngines = it.toInt() },
                                    valueRange = 0f..AppState.rocket.getMaxEngines(it).toFloat()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}