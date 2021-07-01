package ws.worldshine.weatherapp.core

interface BaseWeatherModel {

    val zipCode: String
    val name: String
    val temperature: String
    val humidity: String
    val windSpeed: String
    val visibility: String
    val sunrise: String
    val sunset: String

}