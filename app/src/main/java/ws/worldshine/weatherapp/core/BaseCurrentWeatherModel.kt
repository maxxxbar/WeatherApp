package ws.worldshine.weatherapp.core


interface BaseCurrentWeatherModel {
    val location: String
    val temperature: Double
    val windSpeed: Double
    val humidity: Int
    val visibility: Int
    val sunrise: String
    val sunset: String
}
