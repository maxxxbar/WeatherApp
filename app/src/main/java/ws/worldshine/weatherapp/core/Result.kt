package ws.worldshine.weatherapp.core

sealed class Result<out E, out R> {
    data class Success<out T>(val data: T) : Result<T, Nothing>()
    data class Error<out S>(val exception: S) : Result<Nothing, S>()
}
