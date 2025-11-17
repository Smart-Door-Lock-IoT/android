package id.my.smartdoorlock.core

sealed class UiState<out T> {
    object Initial : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Failure(val message: String = "Terjadi kesalahan tak terduga!") : UiState<Nothing>()
}

fun UiState<*>.isLoading() = this is UiState.Loading
fun UiState<*>.isSuccess() = this is UiState.Success
fun UiState<*>.isFailure() = this is UiState.Failure

inline fun <T> UiState<T>.onLoading(action: () -> Unit): UiState<T> {
    if (this is UiState.Loading) action()
    return this
}

inline fun <T> UiState<T>.onSuccess(action: (T) -> Unit): UiState<T> {
    if (this is UiState.Success) action(data)
    return this
}

inline fun <T> UiState<T>.onFailure(action: (String) -> Unit): UiState<T> {
    if (this is UiState.Failure) action(message)
    return this
}