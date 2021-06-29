package ws.worldshine.weatherapp.core

import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes resId: Int): String

}