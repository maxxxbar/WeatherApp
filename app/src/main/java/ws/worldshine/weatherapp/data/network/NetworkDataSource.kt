package ws.worldshine.weatherapp.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ws.worldshine.weatherapp.core.BaseDataSource
import ws.worldshine.weatherapp.core.BaseWeatherModel
import ws.worldshine.weatherapp.core.Result
import ws.worldshine.weatherapp.core.ServiceUnavailableException
import ws.worldshine.weatherapp.util.toFormatDateTime

class NetworkDataSource(private val service: WeatherService) : BaseDataSource {

    override suspend fun get(zipCode: String): Result<BaseWeatherModel, Exception> = withContext(Dispatchers.IO) {
        try {
            Result.Success(fetchWeather(zipCode))
        } catch (e: Exception) {
            Result.Error(ServiceUnavailableException())
        }
    }

    private suspend fun fetchWeather(
        zipCode: String
    ): WeatherModel {
        val result = service.fetchWeather(zipCode)
        return WeatherModel(
            zipCode = zipCode,
            name = result.name,
            temperature = result.main.temp,
            humidity = result.main.humidity,
            windSpeed = result.wind.speed,
            visibility = result.visibility,
            sunrise = result.sys.sunrise.toFormatDateTime(),
            sunset = result.sys.sunset.toFormatDateTime()
        )
    }

}