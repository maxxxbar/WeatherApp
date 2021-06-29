package ws.worldshine.weatherapp.presentation

import ws.worldshine.weatherapp.R
import ws.worldshine.weatherapp.core.Failure
import ws.worldshine.weatherapp.core.NoDataException
import ws.worldshine.weatherapp.core.ResourceManager
import ws.worldshine.weatherapp.core.ServiceUnavailableException

class BaseFailure(private val resourceManager: ResourceManager) : Failure {

    override fun getMessage(e: Exception?): String {
        return when (e) {
            is ServiceUnavailableException -> resourceManager.getString(R.string.service_unavailable)
            is NoDataException -> resourceManager.getString(R.string.no_data)
            else -> resourceManager.getString(R.string.unknown_error)
        }
    }

}