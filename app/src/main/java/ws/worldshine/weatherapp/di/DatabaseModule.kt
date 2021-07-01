package ws.worldshine.weatherapp.di

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import ws.worldshine.weatherapp.data.db.WeatherDatabase

private const val DB_NAME = "weatherDB"
val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideWeatherDao(get()) }
}

fun provideDatabase(application: Application) =
    Room.databaseBuilder(application, WeatherDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

fun provideWeatherDao(database: WeatherDatabase) = database.weatherDao