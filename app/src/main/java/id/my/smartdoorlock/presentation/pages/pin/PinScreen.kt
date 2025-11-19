package id.my.smartdoorlock.presentation.pages.pin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardReturn
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.my.smartdoorlock.core.LocalNavBackStack
import id.my.smartdoorlock.core.Routes
import id.my.smartdoorlock.core.UiState
import id.my.smartdoorlock.core.isLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun PinScreen() {
    val viewModel = koinViewModel<PinViewModel>()

    val backStack = LocalNavBackStack.current
    val snackbarHostState = remember { SnackbarHostState() }

    var pin by remember { mutableStateOf("") }

    LaunchedEffect(viewModel.validatePinState) {
        with(viewModel.validatePinState) {
            when (this) {
                is UiState.Success -> {
                    backStack.removeAll { true }
                    backStack.add(Routes.Home)
                }

                is UiState.Failure -> snackbarHostState.showSnackbar(
                    message = message
                )

                else -> Unit
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            ) {
                pin.forEach { item ->
                    Icon(
                        Icons.Rounded.Circle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.padding(32.dp),
                userScrollEnabled = false,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(12) {
                    when (it) {
                        9 -> Button(
                            onClick = {
                                pin = ""
                            },
                            modifier = Modifier.aspectRatio(1f)
                        ) {
                            Icon(
                                Icons.Rounded.Close,
                                contentDescription = null
                            )
                        }

                        11 -> Button(
                            onClick = {
                                viewModel.validatePin(
                                    pin = pin
                                )
                            },
                            modifier = Modifier.aspectRatio(1f),
                            enabled = !viewModel.validatePinState.isLoading()
                        ) {
                            when (viewModel.validatePinState) {
                                UiState.Loading -> CircularProgressIndicator()
                                else -> Icon(
                                    Icons.AutoMirrored.Rounded.KeyboardReturn,
                                    contentDescription = null
                                )
                            }
                        }

                        else -> TextButton(
                            onClick = {
                                pin += when (it == 10) {
                                    true -> "0"
                                    else -> "${it + 1}"
                                }
                            },
                            modifier = Modifier.aspectRatio(1f)
                        ) {
                            Text(
                                when (it == 10) {
                                    true -> "0"
                                    else -> "${it + 1}"
                                },
                                style = MaterialTheme.typography.titleLarge,
                            )
                        }
                    }
                }
            }
        }
    }
}