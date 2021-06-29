package ws.worldshine.weatherapp.data.db

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ws.worldshine.weatherapp.core.BaseCacheDataSource
import ws.worldshine.weatherapp.core.BaseWeatherModel
import ws.worldshine.weatherapp.core.NoCacheException
import ws.worldshine.weatherapp.core.Result

class CacheDataSource(private val cacheDao: CacheDao) : BaseCacheDataSource {

    override suspend fun set(baseWeatherModel: BaseWeatherModel) =
        cacheDao.insertWeatherItem(baseModelToCacheModel(baseWeatherModel))

    override suspend fun get(zipCode: String): Result<BaseWeatherModel, Exception> =
        withContext(Dispatchers.IO) {
            val item = cacheDao.getCacheItem(zipCode)
            return@withContext if (item == null) {
                Result.Error(NoCacheException())
            } else {
                Result.Success(item)
            }
        }

    private fun baseModelToCacheModel(baseWeatherModel: BaseWeatherModel) =
        CacheModel(
            zipCode = baseWeatherModel.zipCode,
            name = baseWeatherModel.name,
            temperature = baseWeatherModel.temperature,
            humidity = baseWeatherModel.humidity,
            windSpeed = baseWeatherModel.windSpeed,
            visibility = baseWeatherModel.visibility,
            sunrise = baseWeatherModel.sunrise,
            sunset = baseWeatherModel.sunset
        )

}