package id.my.smartdoorlock.presentation.pages.pin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.smartdoorlock.core.UiState
import id.my.smartdoorlock.core.extensions.toFailure
import id.my.smartdoorlock.openapi.apis.ConfigurationApi
import id.my.smartdoorlock.openapi.models.ValidatePinRequest
import id.my.smartdoorlock.openapi.models.ValidatePinResponse
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch

class PinViewModel(
    private val configurationApi: ConfigurationApi
) : ViewModel() {
    var validatePinState by mutableStateOf<UiState<ValidatePinResponse>>(UiState.Initial)
        private set

    fun validatePin(pin: String) = viewModelScope.launch {
        try {
            validatePinState = UiState.Loading
            val body = configurationApi.validatePin(
                ValidatePinRequest(
                    pin = pin
                )
            ).body()
            validatePinState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            validatePinState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            validatePinState = UiState.Failure()
        }
    }
}