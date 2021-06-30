package ws.worldshine.weatherapp.core

interface BaseCacheDataSource {

    suspend fun get(zipCode: String): Result<BaseWeatherModel, Exception>


    suspend fun set(baseWeatherModel: BaseWeatherModel)

}