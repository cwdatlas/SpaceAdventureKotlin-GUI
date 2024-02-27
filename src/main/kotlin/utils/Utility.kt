package utils

import androidx.compose.ui.input.pointer.PointerIcon
import java.awt.Cursor

/**
 * Utils holds useful functions that can be packed away easily decreasing clutter.
 */

/**
 * handCursor() makes the process of getting the HAND_CURSOR so users can tell that they are hovering over a clickable object
 * Directly copied from the Tutorial mentioned in readme by YoursSohail.
 */
fun handCursor() = PointerIcon(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
