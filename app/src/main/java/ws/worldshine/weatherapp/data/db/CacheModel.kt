package ws.worldshine.weatherapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import ws.worldshine.weatherapp.core.BaseWeatherModel

@Entity(tableName = "cache")
data class CacheModel(

    @PrimaryKey override val zipCode: String,
    override val name: String,
    override val temperature: String,
    override val humidity: String,
    override val windSpeed: String,
    override val visibility: String,
    override val sunrise: String,
    override val sunset: String

) : BaseWeatherModel
