package id.my.smartdoorlock.presentation.pages.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import id.my.smartdoorlock.core.LocalNavBackStack
import id.my.smartdoorlock.core.Routes
import id.my.smartdoorlock.core.isLoading
import id.my.smartdoorlock.core.isSuccess
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel = koinViewModel<HomeViewModel>()

    val backStack = LocalNavBackStack.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(
        viewModel.triggerOpenDoorState,
        viewModel.triggerFingerprintModeState,
        viewModel.triggerRFIDModeState
    ) {
        with(viewModel.triggerOpenDoorState) {
            if (this.isSuccess()) {
                scope.launch {
                    snackbarHostState.showSnackbar("Kunci pintu berhasil dibuka!")
                }
            }
        }

        with(viewModel.triggerFingerprintModeState) {
            if (this.isSuccess()) {
                scope.launch {
                    snackbarHostState.showSnackbar("Mode penambahan sidik jari diaktifkan!")
                }
            }
        }

        with(viewModel.triggerRFIDModeState) {
            if (this.isSuccess()) {
                scope.launch {
                    snackbarHostState.showSnackbar("Mode penambahan RFID diaktifkan!")
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Smart Door Lock") }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Button(
                onClick = {
                    viewModel.triggerOpenDoor()
                },
                enabled = !viewModel.triggerOpenDoorState.isLoading()
            ) {
                Text("Buka kunci")
            }
            Button(
                onClick = {
                    backStack.add(Routes.ChangePin)
                }
            ) {
                Text("Ubah pin aplikasi")
            }
            Button(
                onClick = {
                    viewModel.triggerFingerprintMode()
                },
                enabled = !viewModel.triggerFingerprintModeState.isLoading()
            ) {
                Text("Mode penambahan sidik jari")
            }
            Button(
                onClick = {
                    viewModel.triggerRFIDMode()
                },
                enabled = !viewModel.triggerRFIDModeState.isLoading()
            ) {
                Text("Mode penambahan RFID")
            }
            Button(
                onClick = {
                    backStack.add(Routes.LogActivities)
                }
            ) {
                Text("Log aktivitas")
            }
        }
    }
}