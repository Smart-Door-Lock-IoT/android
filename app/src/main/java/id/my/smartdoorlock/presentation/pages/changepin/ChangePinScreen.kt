package id.my.smartdoorlock.presentation.pages.changepin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardReturn
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.my.smartdoorlock.core.LocalNavBackStack
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePinScreen() {
    val viewModel = koinViewModel<ChangePinViewModel>()

    val backStack = LocalNavBackStack.current
    var pin by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            backStack.removeLastOrNull()
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text("Ubah Pin")
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text(pin)
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
                                viewModel.changePin(
                                    newPin = pin
                                )
                            },
                            modifier = Modifier.aspectRatio(1f)
                        ) {
                            Icon(
                                Icons.AutoMirrored.Rounded.KeyboardReturn,
                                contentDescription = null
                            )
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