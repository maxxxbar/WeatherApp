package ws.worldshine.weatherapp.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Dispatchers;
import ws.worldshine.weatherapp.core.BaseWeatherModel;
import ws.worldshine.weatherapp.domain.WeatherUiModel;
import ws.worldshine.weatherapp.domain.WeatherUseCase;

public class MainFragmentViewModel extends ViewModel {
    private final WeatherUseCase weatherUseCase;
    private final MutableLiveData<BaseWeatherModel> weatherData = new MutableLiveData<>();
    private final MutableLiveData<String> errorData = new MutableLiveData<>();

    public LiveData<String> getErrorData() {
        return errorData;
    }

    public MainFragmentViewModel(WeatherUseCase weatherUseCase) {
        this.weatherUseCase = weatherUseCase;
    }

    private void fetchWeather(String zipCode) {
        weatherUseCase.fetchWeather(zipCode, new Continuation<WeatherUiModel>() {
            @NotNull
            @Override
            public CoroutineContext getContext() {
                return Dispatchers.getMain();
            }

            @Override
            public void resumeWith(@NotNull Object o) {
                if (o instanceof WeatherUiModel.Success) {
                    BaseWeatherModel weather = (BaseWeatherModel) ((WeatherUiModel.Success<?, ?>) o).getData();
                    String error = (String) ((WeatherUiModel.Success<?, ?>) o).getError();
                    weatherData.setValue(weather);
                    if (error.length() > 0) {
                        errorData.setValue(error);
                    }
                } else {
                    String error = (String) ((WeatherUiModel.Error<?>) o).getError();
                    errorData.setValue(error);
                }
            }
        });
    }

    public LiveData<BaseWeatherModel> getWeatherData(String zipCode) {
        fetchWeather(zipCode);
        return weatherData;
    }

}