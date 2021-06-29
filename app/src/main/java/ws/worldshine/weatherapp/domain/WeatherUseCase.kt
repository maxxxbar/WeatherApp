package ws.worldshine.weatherapp.domain

import ws.worldshine.weatherapp.core.Failure
import ws.worldshine.weatherapp.core.Result
import ws.worldshine.weatherapp.core.ServiceUnavailableException
import ws.worldshine.weatherapp.core.WeatherRepository

class WeatherUseCase(
    private val weatherRepository: WeatherRepository,
    private val failure: Failure) {

    suspend fun fetchWeather(zipCode: String): WeatherUiModel {
        return when (val result = weatherRepository.load(zipCode)) {
            is Result.Success -> {
                when (result.data.second) {
                    null -> WeatherUiModel.Success(result.data.first, "")
                    is ServiceUnavailableException -> WeatherUiModel.Success(result.data.first, failure.getMessage(result.data.second))
                    else -> WeatherUiModel.Success(result.data.first, failure.getMessage(result.data.second))
                }
            }
            is Result.Error -> {
                WeatherUiModel.Error(failure.getMessage(result.exception))
            }
        }
    }

}