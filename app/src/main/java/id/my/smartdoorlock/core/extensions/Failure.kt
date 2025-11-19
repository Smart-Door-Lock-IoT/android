package id.my.smartdoorlock.core.extensions

import com.google.gson.Gson

data class Failure(
    val message: String
)

fun String.toFailure(): Failure = try {
    if (this.isNotEmpty())
        Gson().fromJson(this, Failure::class.java)
    else
        Failure(message = "Terjadi kesalahan tak terduga! empty")
} catch (e: Exception) {
    e.printStackTrace()
    Failure(message = "Terjadi kesalahan tak terduga! ${e.message}")
}