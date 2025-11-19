package id.my.smartdoorlock.presentation.pages.logactivities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.smartdoorlock.core.UiState
import id.my.smartdoorlock.core.extensions.toFailure
import id.my.smartdoorlock.openapi.apis.ControlApi
import id.my.smartdoorlock.openapi.models.DeleteAllLogsResponse
import id.my.smartdoorlock.openapi.models.GetAllLogsResponse
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LogActivitiesViewModel(
    private val controlApi: ControlApi
) : ViewModel() {
    var deleteAllLogsState by mutableStateOf<UiState<DeleteAllLogsResponse>>(UiState.Initial)
        private set

    fun deleteAllLog() = viewModelScope.launch {
        try {
            deleteAllLogsState = UiState.Loading
            val body = controlApi.deleteAllLogs().body()
            deleteAllLogsState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            deleteAllLogsState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            deleteAllLogsState = UiState.Failure()
        } finally {
            delay(100)
            deleteAllLogsState = UiState.Initial
        }
    }

    var getAllLogsState by mutableStateOf<UiState<GetAllLogsResponse>>(UiState.Initial)
        private set

    fun getAllLogs() = viewModelScope.launch {
        try {
            getAllLogsState = UiState.Loading
            val body = controlApi.getAllLogs().body()
            getAllLogsState = UiState.Success(body)
        } catch (e: ResponseException) {
            e.printStackTrace()
            getAllLogsState = UiState.Failure(
                message = e.response.bodyAsText().toFailure().message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            getAllLogsState = UiState.Failure()
        }
    }

    init {
        getAllLogs()
    }
}