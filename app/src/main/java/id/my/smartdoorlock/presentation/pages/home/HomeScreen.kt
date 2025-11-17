package id.my.smartdoorlock.presentation.pages.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import id.my.smartdoorlock.core.LocalNavBackStack
import id.my.smartdoorlock.core.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val backStack = LocalNavBackStack.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Smart Door Lock") }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Button(onClick = {}) {
                Text("Buka kunci")
            }
            Button(
                onClick = {
                    backStack.add(Routes.ChangePin)
                }
            ) {
                Text("Ubah pin aplikasi")
            }
            Button(onClick = {}) {
                Text("Mode penambahan sidik jari")
            }
            Button(onClick = {}) {
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