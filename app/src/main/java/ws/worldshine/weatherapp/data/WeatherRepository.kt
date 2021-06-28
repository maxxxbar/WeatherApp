package ws.worldshine.weatherapp.data

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ws.worldshine.weatherapp.core.Result
import ws.worldshine.weatherapp.data.db.CacheDataSource
import ws.worldshine.weatherapp.data.network.NetworkDataSource

class WeatherRepository(
    private val networkDataSource: NetworkDataSource,
    private val cacheDataSource: CacheDataSource
) {
    private val TAG = javaClass.simpleName
    fun load(zipCode: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val cacheItem = cacheDataSource.get(zipCode)
            val networkItem = networkDataSource.get(zipCode)
            Log.d(TAG, "cacheItem: $cacheItem")
            Log.d(TAG, "networkItem: $networkItem")
            if (cacheItem is Result.Error) {
                if (networkItem is Result.Success) {
                    cacheDataSource.set(networkItem.data)
                }
            }
        }
    }
}