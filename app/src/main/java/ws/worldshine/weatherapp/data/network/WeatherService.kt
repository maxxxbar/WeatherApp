package ws.worldshine.weatherapp.data.network

import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {

    private companion object {
        private const val API_KEY = "4728750f30468f396c139ef1d649d04b"
    }

    @GET("weather?&units=imperial&appid=$API_KEY")
    suspend fun fetchWeather(@Query("zip") zipCode: String): WeatherNetworkModel

}