package ws.worldshine.weatherapp.presentation

import android.content.Context
import ws.worldshine.weatherapp.core.ResourceManager

class BaseResourceManager(private val context: Context) : ResourceManager {

    override fun getString(resId: Int): String = context.getString(resId)

}