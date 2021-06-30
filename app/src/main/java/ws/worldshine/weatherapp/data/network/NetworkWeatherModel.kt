package ws.worldshine.weatherapp.data.network

import ws.worldshine.weatherapp.core.BaseWeatherModel

data class NetworkWeatherModel(

    override val zipCode: String,
    override val name: String,
    override val temperature: String,
    override val humidity: String,
    override val windSpeed: String,
    override val visibility: String,
    override val sunrise: String,
    override val sunset: String

) : BaseWeatherModel
