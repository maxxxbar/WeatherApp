package ws.worldshine.weatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CacheModel::class],
    version = 2,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val weatherDao: CacheDao

}