package ws.worldshine.weatherapp.core

interface BaseWeatherModel {
    val zipCode: Int
    val name: String
    val temperature: Double
    val humidity: Double
    val windSpeed: Double
    val visibility: Int
    val sunrise: Int
    val sunset: Int
}