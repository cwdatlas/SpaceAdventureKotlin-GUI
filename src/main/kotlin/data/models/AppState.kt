package data.models

/**
 * This class stores any state important for use globally.
 * This object is used sparingly.
 */
object AppState {
    val toOrbitDeltaV = 8000
    val useableTWR = 1.5
    var numberOfLaunches = 0
    var previousDeltaV = 0
    // We only have one instance of rocket, so storing it in the AppState should alright
    var rocket = Rocket()
}