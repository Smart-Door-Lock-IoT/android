package id.my.smartdoorlock.core

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Routes {
    @Serializable
    data object Pin : NavKey

    @Serializable
    data object Home : NavKey

    @Serializable
    data object ChangePin : NavKey

    @Serializable
    data object LogActivities : NavKey
}