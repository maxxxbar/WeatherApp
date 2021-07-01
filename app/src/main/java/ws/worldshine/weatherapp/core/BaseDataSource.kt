package ws.worldshine.weatherapp.core

interface BaseDataSource {

    suspend fun get(zipCode: String): Result<BaseWeatherModel, Exception>

}