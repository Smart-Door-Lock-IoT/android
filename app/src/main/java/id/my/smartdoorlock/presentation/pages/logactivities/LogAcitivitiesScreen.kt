package id.my.smartdoorlock.presentation.pages.logactivities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.DeleteForever
import androidx.compose.material.icons.rounded.Fingerprint
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import id.my.smartdoorlock.core.LocalNavBackStack
import id.my.smartdoorlock.core.UiState
import id.my.smartdoorlock.core.extensions.formatDateTime
import id.my.smartdoorlock.core.isLoading
import id.my.smartdoorlock.core.isSuccess
import id.my.smartdoorlock.openapi.models.DomainsLog
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogActivitiesScreen() {
    val viewModel = koinViewModel<LogActivitiesViewModel>()

    val backStack = LocalNavBackStack.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var isDialogOpen by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.deleteAllLogsState) {
        with(viewModel.deleteAllLogsState) {
            if (this.isSuccess()) {
                scope.launch {
                    isDialogOpen = false
                    snackbarHostState.showSnackbar("Berhasil menghapus semua log aktivitas!")
                    viewModel.getAllLogs()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },
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
                    Text("Log Aktivitas")
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.getAllLogs()
                        },
                        enabled = !viewModel.getAllLogsState.isLoading()
                    ) {
                        Icon(
                            Icons.Rounded.Refresh,
                            contentDescription = null
                        )
                    }
                    IconButton(
                        onClick = {
                            isDialogOpen = true
                        },
                        enabled = !viewModel.getAllLogsState.isLoading()
                    ) {
                        Icon(
                            Icons.Rounded.DeleteForever,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            with(viewModel.getAllLogsState) {
                when (this) {
                    UiState.Loading -> item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                    is UiState.Success -> items(data.logs) {
                        CustomListItemLog(data = it)
                    }

                    else -> Unit
                }
            }
        }
    }

    if (isDialogOpen)
        AlertDialog(
            onDismissRequest = {
                if (!viewModel.deleteAllLogsState.isLoading())
                    isDialogOpen = false
            },
            title = {
                Text("Hapus Log Aktivitas")
            },
            text = {
                Text(
                    "Apakah Anda yakin akan menghapus semua log aktivitas? " +
                            "Tindakan yang Anda lakukan tidak dapat dipulihkan."
                )
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        isDialogOpen = false
                    },
                    enabled = !viewModel.deleteAllLogsState.isLoading()
                ) {
                    Text("Batal")
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteAllLog()
                    },
                    enabled = !viewModel.deleteAllLogsState.isLoading()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            ButtonDefaults.IconSpacing
                        )
                    ) {
                        if (viewModel.deleteAllLogsState.isLoading())
                            CircularProgressIndicator(
                                modifier = Modifier.size(ButtonDefaults.IconSize),
                                strokeWidth = 2.4.dp
                            )
                        Text("Hapus")
                    }
                }
            }
        )
}

@Composable
private fun CustomListItemLog(data: DomainsLog) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(
                    when (data.status) {
                        true -> MaterialTheme.colorScheme.primaryContainer
                        else -> MaterialTheme.colorScheme.errorContainer
                    }
                )
        ) {
            when (data.deviceName == "Fingerprint") {
                true -> Icon(
                    Icons.Rounded.Fingerprint,
                    contentDescription = null,
                    modifier = Modifier
                        .size(ButtonDefaults.IconSize)
                        .align(Alignment.Center),
                    tint = when (data.status) {
                        true -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.error
                    }
                )

                else -> Icon(
                    Icons.Rounded.CreditCard,
                    contentDescription = null,
                    modifier = Modifier
                        .size(ButtonDefaults.IconSize)
                        .align(Alignment.Center),
                    tint = when (data.status) {
                        true -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.error
                    }
                )
            }
        }
        Column {
            Text(data.deviceName, style = MaterialTheme.typography.titleSmall)
            Text(
                "${
                    when (data.status) {
                        true -> "Berhasil membuka kunci pintu"
                        else -> "Gagal membuka kunci pintu"
                    }
                } pada ${data.createdAt.formatDateTime()} pukul ${data.createdAt.formatDateTime("HH:mm")}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .72f)
            )
        }
    }
}