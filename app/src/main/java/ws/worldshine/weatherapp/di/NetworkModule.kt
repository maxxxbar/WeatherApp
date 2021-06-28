package ws.worldshine.weatherapp.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ws.worldshine.weatherapp.data.network.WeatherService

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
val networkModule = module {
    factory { provideGsonConverterFactory() }
    factory { provideOkHttpClient() }
    factory { provideWeatherService(get()) }
    single { provideRetrofit(get(), get()) }
}

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun provideWeatherService(retrofit: Retrofit): WeatherService =
    retrofit.create(WeatherService::class.java)

fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    val okHttpClient = OkHttpClient.Builder()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    okHttpClient.addInterceptor(loggingInterceptor)
    return okHttpClient.build()
}
