package ws.worldshine.weatherapp.data.network

import ws.worldshine.weatherapp.core.BaseDataSource
import ws.worldshine.weatherapp.core.BaseWeatherModel
import ws.worldshine.weatherapp.core.Result

class NetworkDataSource(private val service: WeatherService) : BaseDataSource {
    override suspend fun get(zipCode: Int): Result<BaseWeatherModel, Exception> = try {
        Result.Success(fetchWeather(zipCode))
    } catch (e: Exception) {
        Result.Error(e)
    }

    private suspend fun fetchWeather(
        zipCode: Int
    ): WeatherModel {
        val result = service.fetchWeather(zipCode)
        return WeatherModel(
            zipCode = zipCode,
            name = result.name,
            temperature = result.main.temp,
            humidity = result.main.humidity,
            windSpeed = result.wind.speed,
            visibility = result.visibility,
            sunrise = result.sys.sunrise,
            sunset = result.sys.sunset
        )
    }
}