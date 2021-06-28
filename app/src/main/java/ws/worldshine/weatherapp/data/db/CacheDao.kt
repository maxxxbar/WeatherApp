package ws.worldshine.weatherapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherItem(weatherDaoModel: CacheModel)

    @Query("SELECT * FROM cache WHERE zipCode =:zipCode")
    suspend fun getCacheItem(zipCode: Int): CacheModel?

}