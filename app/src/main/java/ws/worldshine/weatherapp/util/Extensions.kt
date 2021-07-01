package ws.worldshine.weatherapp.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
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

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        view.windowInsetsController?.hide(WindowInsetsCompat.Type.ime())
    } else {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}