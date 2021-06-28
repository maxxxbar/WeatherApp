package ws.worldshine.weatherapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ws.worldshine.weatherapp.core.Result
import ws.worldshine.weatherapp.data.network.WeatherService

class WeatherRepository(private val weatherService: WeatherService) {

    suspend fun load(zipCode: Int) =
        withContext(Dispatchers.IO) {
            try {
                Result.Success(weatherService.fetchWeather(zipCode))
            } catch (e: Exception) {
                Result.Error(e.localizedMessage)
            }
        }
}