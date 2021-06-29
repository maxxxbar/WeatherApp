package ws.worldshine.weatherapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import ws.worldshine.weatherapp.core.BaseWeatherModel

@Entity(tableName = "cache")
data class CacheModel(

    @PrimaryKey override val zipCode: String,
    override val name: String,
    override val temperature: Double,
    override val humidity: Double,
    override val windSpeed: Double,
    override val visibility: Int,
    override val sunrise: String,
    override val sunset: String

) : BaseWeatherModel
