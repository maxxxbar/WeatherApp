package ws.worldshine.weatherapp.core

interface BaseDataSource {
    suspend fun get(zipCode: Int): Result<BaseWeatherModel, Exception>
}