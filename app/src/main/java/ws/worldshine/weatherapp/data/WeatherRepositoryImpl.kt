package ws.worldshine.weatherapp.data

import ws.worldshine.weatherapp.core.BaseWeatherModel
import ws.worldshine.weatherapp.core.NoDataException
import ws.worldshine.weatherapp.core.Result
import ws.worldshine.weatherapp.core.WeatherRepository
import ws.worldshine.weatherapp.data.db.CacheDataSource
import ws.worldshine.weatherapp.data.network.NetworkDataSource

class WeatherRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val cacheDataSource: CacheDataSource
) : WeatherRepository {

    override suspend fun load(zipCode: String): Result<Pair<BaseWeatherModel, Exception?>, Exception> {
        val cacheItem = cacheDataSource.get(zipCode)
        val networkItem = networkDataSource.get(zipCode)
        if (cacheItem is Result.Error && networkItem is Result.Error) return Result.Error(NoDataException())
        return when (networkItem) {
            is Result.Success -> {
                cacheDataSource.set(networkItem.data)
                Result.Success(networkItem.data to null)
            }
            is Result.Error -> {
                when (cacheItem) {
                    is Result.Success -> Result.Success(cacheItem.data to networkItem.exception)
                    is Result.Error -> Result.Error(cacheItem.exception)
                }
            }
        }
    }

}