package ws.worldshine.weatherapp.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ws.worldshine.weatherapp.core.Failure
import ws.worldshine.weatherapp.core.ResourceManager
import ws.worldshine.weatherapp.core.WeatherRepository
import ws.worldshine.weatherapp.data.WeatherRepositoryImpl
import ws.worldshine.weatherapp.data.db.CacheDao
import ws.worldshine.weatherapp.data.db.CacheDataSource
import ws.worldshine.weatherapp.data.network.NetworkDataSource
import ws.worldshine.weatherapp.data.network.WeatherService
import ws.worldshine.weatherapp.domain.WeatherUseCase
import ws.worldshine.weatherapp.presentation.BaseFailure
import ws.worldshine.weatherapp.presentation.BaseResourceManager
import ws.worldshine.weatherapp.screens.main.MainFragmentViewModel

val appModule = module {
    viewModel { MainFragmentViewModel(get()) }
    factory { provideWeatherService(get()) }
    factory { provideNetworkDataSource(get()) }
    factory { provideCacheDataSource(get()) }
    factory { provideWeatherUseCase(get(), get()) }
    factory { provideFailure(get()) }
    single { provideResource(androidContext()) }
    single { provideRepository(get(), get()) }
}

fun provideRepository(networkDataSource: NetworkDataSource, cacheDataSource: CacheDataSource): WeatherRepository =
    WeatherRepositoryImpl(networkDataSource, cacheDataSource)

fun provideNetworkDataSource(service: WeatherService): NetworkDataSource =
    NetworkDataSource(service)

fun provideCacheDataSource(cacheDao: CacheDao) = CacheDataSource(cacheDao)

fun provideWeatherUseCase(weatherRepository: WeatherRepository, failure: Failure) =
    WeatherUseCase(weatherRepository, failure)

fun provideResource(context: Context): ResourceManager = BaseResourceManager(context)

fun provideFailure(resourceManager: ResourceManager): Failure = BaseFailure(resourceManager)