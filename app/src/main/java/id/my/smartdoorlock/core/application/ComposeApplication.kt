package id.my.smartdoorlock.core.application

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import id.my.smartdoorlock.core.LocalNavBackStack
import id.my.smartdoorlock.core.Routes
import id.my.smartdoorlock.presentation.pages.changepin.ChangePinScreen
import id.my.smartdoorlock.presentation.pages.home.HomeScreen
import id.my.smartdoorlock.presentation.pages.logactivities.LogActivitiesScreen
import id.my.smartdoorlock.presentation.pages.pin.PinScreen
import id.my.smartdoorlock.ui.theme.SmartDoorLockTheme

@Composable
fun ComposeApplication() {
    val backStack = rememberNavBackStack(Routes.Home)

    SmartDoorLockTheme(darkTheme = false) {
        Surface {
            CompositionLocalProvider(LocalNavBackStack provides backStack) {
                NavDisplay(
                    entryDecorators = listOf(
                        rememberSaveableStateHolderNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator()
                    ),
                    backStack = backStack,
                    entryProvider = entryProvider {
                        entry<Routes.Pin> { PinScreen() }
                        entry<Routes.Home> { HomeScreen() }
                        entry<Routes.ChangePin> { ChangePinScreen() }
                        entry<Routes.LogActivities> { LogActivitiesScreen() }
                    }
                )
            }
        }
    }
}