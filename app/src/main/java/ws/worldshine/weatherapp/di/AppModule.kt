package ws.worldshine.weatherapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ws.worldshine.weatherapp.data.WeatherRepository
import ws.worldshine.weatherapp.data.db.CacheDao
import ws.worldshine.weatherapp.data.db.CacheDataSource
import ws.worldshine.weatherapp.data.network.NetworkDataSource
import ws.worldshine.weatherapp.data.network.WeatherService
import ws.worldshine.weatherapp.screens.main.MainFragmentViewModel

val appModule = module {
    viewModel { MainFragmentViewModel(get()) }
    factory { provideWeatherService(get()) }
    factory { provideNetworkDataSource(get()) }
    factory { provideCacheDataSource(get()) }
    single { provideRepository(get(), get()) }
}

fun provideRepository(networkDataSource: NetworkDataSource, cacheDataSource: CacheDataSource) =
    WeatherRepository(networkDataSource, cacheDataSource)

fun provideNetworkDataSource(service: WeatherService): NetworkDataSource =
    NetworkDataSource(service)

fun provideCacheDataSource(cacheDao: CacheDao): CacheDataSource = CacheDataSource(cacheDao)