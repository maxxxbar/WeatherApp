package ws.worldshine.weatherapp.core

import okio.IOException

class NoCacheException : NullPointerException()

class ServiceUnavailableException : IOException()

class NoDataException : Exception()