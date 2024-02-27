package data.models

/**
 * @author Aidan Scott
 * This class stores any state important for use globally.
 * This object is used sparingly.
 * by creating an object in the AppState file we can effectively delegate a rocket.
 * Also, this object was used with the intention that it would be where Rocket and other values are always stored,
 * but after having data persistence issues with the statistics in the ActionColumn function,
 * this class was used much less in favor for using delegated vars.
 */
object AppState {
    // consts needed for game:
    // The minimum DeltaV achieve orbit
    const val TOORBITDELTAV = 8000

    // minimum thrust to weight ratio for liftoff
    const val USEABLETWR = 1.5

    // initial rocket instance
    var rocket = Rocket()
}