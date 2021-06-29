package ws.worldshine.weatherapp.domain

sealed interface WeatherUiModel {

    data class Success<T, S>(val data: T, val error: S) : WeatherUiModel
    data class Error<S>(val error: S) : WeatherUiModel

}