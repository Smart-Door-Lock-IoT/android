package id.my.smartdoorlock.presentation.pages.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.smartdoorlock.core.UiState
import id.my.smartdoorlock.core.extensions.toFailure
import id.my.smartdoorlock.openapi.apis.ControlApi
import id.my.smartdoorlock.openapi.models.TriggerBuzzerAlarmResponse
import id.my.smartdoorlock.openapi.models.TriggerFingerprintModeResponse
import id.my.smartdoorlock.openapi.models.TriggerOpenDoorResponse
import id.my.smartdoorlock.openapi.models.TriggerRFIDModeResponse
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val controlApi: ControlApi
) : ViewModel() {
    var triggerOpenDoorState by mutableStateOf<UiState<TriggerOpenDoorResponse>>(UiState.Initial)
        private set

    fun triggerOpenDoor() = viewModelScope.launch {
        try {
            triggerOpenDoorState = UiState.Loading
            val body = controlApi.triggerOpenDoor().body()
            triggerOpenDoorState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            triggerOpenDoorState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            triggerOpenDoorState = UiState.Failure()
        } finally {
            delay(100)
            triggerOpenDoorState = UiState.Initial
        }
    }

    var triggerBuzzerAlarmState by mutableStateOf<UiState<TriggerBuzzerAlarmResponse>>(UiState.Initial)
        private set

    fun triggerBuzzerAlarm() = viewModelScope.launch {
        try {
            triggerBuzzerAlarmState = UiState.Loading
            val body = controlApi.triggerBuzzerAlarm().body()
            triggerBuzzerAlarmState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            triggerBuzzerAlarmState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            triggerBuzzerAlarmState = UiState.Failure()
        } finally {
            delay(100)
            triggerBuzzerAlarmState = UiState.Initial
        }
    }

    var triggerFingerprintModeState by mutableStateOf<UiState<TriggerFingerprintModeResponse>>(
        UiState.Initial
    )
        private set

    fun triggerFingerprintMode() = viewModelScope.launch {
        try {
            triggerFingerprintModeState = UiState.Loading
            val body = controlApi.triggerFingerprintMode().body()
            triggerFingerprintModeState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            triggerFingerprintModeState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            triggerFingerprintModeState = UiState.Failure()
        } finally {
            delay(100)
            triggerFingerprintModeState = UiState.Initial
        }
    }

    var triggerRFIDModeState by mutableStateOf<UiState<TriggerRFIDModeResponse>>(UiState.Initial)
        private set

    fun triggerRFIDMode() = viewModelScope.launch {
        try {
            triggerRFIDModeState = UiState.Loading
            val body = controlApi.triggerRFIDMode().body()
            triggerRFIDModeState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            triggerRFIDModeState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            triggerRFIDModeState = UiState.Failure()
        } finally {
            delay(100)
            triggerRFIDModeState = UiState.Initial
        }
    }
}