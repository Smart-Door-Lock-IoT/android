package id.my.smartdoorlock.core.extensions

import android.os.Build
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Long.formatDateTime(pattern: String = "EEEE, dd MMMM yyyy"): String = try {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val instant = Instant.ofEpochMilli(this)
        val zonedDateTime = instant.atZone(ZoneId.systemDefault())

        val outputFormatter = DateTimeFormatter.ofPattern(
            pattern,
            Locale.getDefault(),
        )

        zonedDateTime.format(outputFormatter).toString()
    } else {
        this.toString()
    }
} catch (_: Exception) {
    this.toString()
}

fun String.formatDateTime(pattern: String = "EEEE, dd MMMM yyyy"): String {
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && this.isNotEmpty()) {
            val isoFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
            val zonedDateTime = ZonedDateTime.parse(this, isoFormatter)

            val targetZoneId = ZoneId.systemDefault()
            val zonedDateTimeInWib = zonedDateTime.withZoneSameInstant(targetZoneId)

            val outputFormatter = DateTimeFormatter.ofPattern(
                pattern,
                Locale.getDefault()
            )

            return zonedDateTimeInWib.format(outputFormatter).toString()
        } else {
            return this
        }
    } catch (_: Exception) {
        return this
    }
}