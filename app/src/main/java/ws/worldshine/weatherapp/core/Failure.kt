package ws.worldshine.weatherapp.core

interface Failure {

    fun getMessage(e: Exception?): String

}