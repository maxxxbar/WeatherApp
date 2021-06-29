package ws.worldshine.weatherapp.data.network

import ws.worldshine.weatherapp.core.BaseWeatherModel

data class WeatherModel(

    override val zipCode: String,
    override val name: String,
    override val temperature: Double,
    override val humidity: Double,
    override val windSpeed: Double,
    override val visibility: Int,
    override val sunrise: String,
    override val sunset: String

) : BaseWeatherModel
