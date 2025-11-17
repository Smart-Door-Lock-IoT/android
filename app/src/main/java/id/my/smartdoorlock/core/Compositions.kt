package id.my.smartdoorlock.core

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

val LocalNavBackStack = compositionLocalOf<NavBackStack<NavKey>> {
    error("No NavBackStack found")
}