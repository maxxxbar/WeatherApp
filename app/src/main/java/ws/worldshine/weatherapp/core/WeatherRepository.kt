package ws.worldshine.weatherapp.core

interface WeatherRepository {

    suspend fun load(zipCode: String): Result<Pair<BaseWeatherModel, Exception?>, Exception>

}