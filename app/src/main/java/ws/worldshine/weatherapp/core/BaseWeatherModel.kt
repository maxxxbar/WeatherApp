package ws.worldshine.weatherapp.core

interface BaseWeatherModel {

    val zipCode: String
    val name: String
    val temperature: Double
    val humidity: Double
    val windSpeed: Double
    val visibility: Int
    val sunrise: String
    val sunset: String

}