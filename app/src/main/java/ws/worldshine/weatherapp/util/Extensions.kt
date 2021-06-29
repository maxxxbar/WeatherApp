package ws.worldshine.weatherapp.util

import android.util.Log
import kotlinx.datetime.Instant
import ws.worldshine.weatherapp.BuildConfig
import java.text.SimpleDateFormat
import java.util.*


fun Long.toFormatDateTime(
    format: String = "MM/dd/yyyy hh:mm:ss aa",
    locale: Locale = Locale.US
): String = try {
    val sdf = SimpleDateFormat(format, locale)
    val dateTime = Date(Instant.fromEpochSeconds(this).toEpochMilliseconds())
    sdf.format(dateTime)
} catch (e: Exception) {
    if (BuildConfig.DEBUG) Log.e("toFormatDateTime", e.localizedMessage, e)
    ""
}