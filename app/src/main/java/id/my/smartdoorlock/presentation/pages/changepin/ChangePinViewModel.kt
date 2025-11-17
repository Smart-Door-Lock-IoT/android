package id.my.smartdoorlock.presentation.pages.changepin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.smartdoorlock.core.UiState
import id.my.smartdoorlock.core.extensions.toFailure
import id.my.smartdoorlock.openapi.apis.ConfigurationApi
import id.my.smartdoorlock.openapi.models.ChangePinRequest
import id.my.smartdoorlock.openapi.models.ChangePinResponse
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch

class ChangePinViewModel(
    private val configurationApi: ConfigurationApi
) : ViewModel() {
    var changePinState by mutableStateOf<UiState<ChangePinResponse>>(UiState.Initial)
        private set

    fun changePin(newPin: String) = viewModelScope.launch {
        try {
            changePinState = UiState.Loading
            val body = configurationApi.changePin(
                ChangePinRequest(
                    newPin = newPin
                )
            ).body()
            changePinState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            changePinState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            changePinState = UiState.Failure()
        }
    }
}