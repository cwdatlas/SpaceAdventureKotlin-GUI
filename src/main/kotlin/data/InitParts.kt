package data

import data.models.Capsule
import data.models.Engine
import data.models.Tank

/**
 * @author Aidan Scott
 * InitParts holds the information that will be needed statically across the app.
 * It initializes objects like capsules, tanks and engines.
 * TODO: merge this file together with AppState, there is not need to have two static object holders for global reference.
 */
object InitParts {
    // Init capsules
    val capsules = mapOf(
        "apollo" to Capsule("Apollo", 11900, 3),
        "dragon" to Capsule("Dragon", 12519, 4),
        "orion" to Capsule("Orion", 10400, 4)
    )

    // Init tanks
    val tanks = mapOf(
        "small" to Tank("small", 10000, 75000, 2.4),
        "medium" to Tank("medium", 27000, 400700, 3.7),
        "large" to Tank("large", 137000, 2077000, 10.1)
    )

    // Init engines
    val engines = mapOf(
        "f-1" to Engine("F-1", 8400, 7770, 304, 3.7),
        "raptor" to Engine("Raptor", 1600, 2690, 363, 1.3),
        "rd-170" to Engine("RD-170", 9750, 7900, 337, 3.8)
    )
}