package ws.worldshine.weatherapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ws.worldshine.weatherapp.data.WeatherRepository
import ws.worldshine.weatherapp.screens.main.MainFragmentViewModel

val appModule = module {
    viewModel { MainFragmentViewModel(get()) }
    factory { WeatherRepository(get()) }
}