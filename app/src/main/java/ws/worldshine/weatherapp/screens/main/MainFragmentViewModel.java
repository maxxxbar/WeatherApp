package ws.worldshine.weatherapp.screens.main;

import androidx.lifecycle.ViewModel;

import ws.worldshine.weatherapp.data.WeatherRepository;

public class MainFragmentViewModel extends ViewModel {
    private final WeatherRepository weatherRepository;

    public MainFragmentViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public void fetchWeather(int zipCode) {
        weatherRepository.load(zipCode);
    }

}