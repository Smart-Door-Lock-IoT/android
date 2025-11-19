package id.my.smartdoorlock.presentation.pages.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Campaign
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Fingerprint
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.LockOpen
import androidx.compose.material.icons.rounded.Pin
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import id.my.smartdoorlock.core.LocalNavBackStack
import id.my.smartdoorlock.core.Routes
import id.my.smartdoorlock.core.UiState
import id.my.smartdoorlock.core.isLoading
import id.my.smartdoorlock.core.isSuccess
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

private enum class SlotType {
    INITIAL,
    FINGERPRINT,
    RFID
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel = koinViewModel<HomeViewModel>()

    val backStack = LocalNavBackStack.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var selectedSlotType by remember { mutableStateOf(SlotType.INITIAL) }
    var selectedSlot by remember { mutableIntStateOf(0) }
    var showBottomSheet by remember { mutableStateOf(false) }

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
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        selectedSlotType = SlotType.INITIAL
                        showBottomSheet = false
                    }

                    scope.launch {
                        snackbarHostState.showSnackbar("Mode penambahan RFID diaktifkan!")
                    }
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
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors().copy(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = .32f)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .size(56.dp)
                        ) {
                            Icon(
                                Icons.Rounded.Lock,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Terkunci", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "Pintu Anda aman dan terkunci.",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    with(viewModel.triggerOpenDoorState) {
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                viewModel.triggerOpenDoor()
                            },
                            enabled = !this.isLoading()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(
                                    ButtonDefaults.IconSpacing
                                )
                            ) {
                                when (this) {
                                    UiState.Loading -> CircularProgressIndicator(
                                        strokeWidth = 2.4.dp,
                                        modifier = Modifier.size(
                                            ButtonDefaults.IconSize
                                        )
                                    )

                                    else -> Icon(
                                        Icons.Rounded.LockOpen,
                                        contentDescription = null,
                                        modifier = Modifier.size(
                                            ButtonDefaults.IconSize
                                        )
                                    )
                                }
                                Text("Buka kunci")
                            }
                        }
                    }
                    with(viewModel.triggerBuzzerAlarmState) {
                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                viewModel.triggerBuzzerAlarm()
                            },
                            enabled = !this.isLoading()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(
                                    ButtonDefaults.IconSpacing
                                )
                            ) {
                                when (this) {
                                    UiState.Loading -> CircularProgressIndicator(
                                        strokeWidth = 2.4.dp,
                                        modifier = Modifier.size(
                                            ButtonDefaults.IconSize
                                        )
                                    )

                                    else -> Icon(
                                        Icons.Rounded.Campaign,
                                        contentDescription = null,
                                        modifier = Modifier.size(
                                            ButtonDefaults.IconSize
                                        )
                                    )
                                }
                                Text("Darurat")
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.size(16.dp)) }

            item {
                Column {
                    Text(
                        "Pengaturan dan Keamanan",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    CustomListItem(
                        title = "Ubah PIN Aplikasi",
                        icon = Icons.Rounded.Pin,
                        onClick = {
                            backStack.add(Routes.ChangePin)
                        }
                    )
                    CustomListItem(
                        title = "Sidik Jari",
                        subtitle = "Masukkan smart door lock device ke mode penambahan sidik jari",
                        icon = Icons.Rounded.Fingerprint,
                        isLoading = viewModel.triggerFingerprintModeState.isLoading(),
                        onClick = {
                            selectedSlotType = SlotType.FINGERPRINT
                            showBottomSheet = true
                        }
                    )
                    CustomListItem(
                        title = "RFID",
                        subtitle = "Masukkan smart door lock ke mode penambahan kartu RFID",
                        icon = Icons.Rounded.CreditCard,
                        isLoading = viewModel.triggerRFIDModeState.isLoading(),
                        onClick = {
                            selectedSlotType = SlotType.RFID
                            showBottomSheet = true
                        }
                    )
                }
            }

            item { Spacer(modifier = Modifier.size(16.dp)) }

            item {
                Text(
                    "Riwayat Aktivitas",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )
            }

            items(5) {
                CustomListItem(
                    title = "Aktivitas ${it + 1}",
                    subtitle = "Detail aktivitas kunci pintu nomor ${it + 1}",
                    icon = Icons.Rounded.LockOpen,
                    onClick = {
                        backStack.add(Routes.LogActivities)
                    }
                )
            }

            item { Spacer(modifier = Modifier.size(32.dp)) }
        }

        // bottom sheet select slot
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    if (!viewModel.triggerRFIDModeState.isLoading() ||
                        !viewModel.triggerFingerprintModeState.isLoading()
                    ) showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                    Text("Pilih Slot", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "Silahkan pilih slot yang akan digunakan untuk menyimpan identitas baru.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .72f)
                    )
                }
                // Sheet content
                for (slot in 1..3) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        headlineContent = {
                            Text("Slot $slot")
                        },
                        trailingContent = {
                            if ((viewModel.triggerFingerprintModeState.isLoading() ||
                                        viewModel.triggerRFIDModeState.isLoading()) &&
                                selectedSlot == slot
                            )
                                CircularProgressIndicator(
                                    modifier = Modifier.size(ButtonDefaults.IconSize),
                                    strokeWidth = 2.4.dp
                                )
                        },
                        modifier = Modifier.clickable {
                            selectedSlot = slot
                            when (selectedSlotType) {
                                SlotType.FINGERPRINT -> viewModel.triggerFingerprintMode(slot)
                                SlotType.RFID -> viewModel.triggerRFIDMode(slot)
                                else -> Unit
                            }
                        }
                    )
                }

                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    enabled = !viewModel.triggerFingerprintModeState.isLoading() ||
                            !viewModel.triggerRFIDModeState.isLoading(),
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }
                ) {
                    Text("Batal")
                }
            }
        }
    }
}

@Composable
private fun CustomListItem(
    title: String,
    subtitle: String = "",
    icon: ImageVector,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !isLoading) { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            when (isLoading) {
                true -> CircularProgressIndicator(
                    modifier = Modifier
                        .size(ButtonDefaults.IconSize)
                        .align(Alignment.Center),
                    strokeWidth = 2.4.dp
                )

                else -> Icon(
                    icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(ButtonDefaults.IconSize)
                        .align(Alignment.Center),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Column {
            Text(title, style = MaterialTheme.typography.titleSmall)
            if (subtitle.isNotEmpty()) {
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .72f)
                )
            }
        }
    }
}