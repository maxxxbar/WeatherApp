package ws.worldshine.weatherapp.screens.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import ws.worldshine.weatherapp.core.BaseWeatherModel;
import ws.worldshine.weatherapp.databinding.MainFragmentBinding;

import static org.koin.java.KoinJavaComponent.get;
import static ws.worldshine.weatherapp.util.ExtensionsKt.hideKeyboard;

public class MainFragment extends Fragment {

    private final MainFragmentViewModel viewModel = get(MainFragmentViewModel.class);

    private MainFragmentBinding binding;
    private TextView location;
    private TextView temperature;
    private TextView windSpeed;
    private TextView humidity;
    private TextView visibility;
    private TextView sunrise;
    private TextView sunset;
    private EditText editText;

    private final View.OnClickListener weatherBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text = editText.getText().toString().trim();
            if (text.length() > 0) {
                viewModel.getWeatherData(text).observe(getViewLifecycleOwner(), weatherModel -> {
                    showWeather(weatherModel);
                    closeKeyboard();
                });

            }
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(inflater, container, false);
        location = binding.tvDescriptionLocation;
        temperature = binding.tvDescriptionTemperature;
        windSpeed = binding.tvDescriptionWindSpeed;
        humidity = binding.tvDescriptionHumidity;
        visibility = binding.tvDescriptionVisibility;
        sunrise = binding.tvDescriptionSunrise;
        sunset = binding.tvDescriptionSunset;
        editText = binding.etZipCode;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rootConstraint.setOnClickListener(v -> closeKeyboard());
        binding.btnGetWeather.setOnClickListener(weatherBtnClick);
        viewModel.getErrorData().observe(getViewLifecycleOwner(), this::showSnackbar);

    }

    private void showSnackbar(String text) {
        Snackbar.make(binding.getRoot(), text, Snackbar.LENGTH_SHORT).show();
    }

    private void closeKeyboard() {
        hideKeyboard(this);
        binding.etZipCode.clearFocus();
    }

    public void showWeather(@NotNull BaseWeatherModel weatherModel) {
        location.setText(weatherModel.getName());
        temperature.setText(weatherModel.getTemperature());
        windSpeed.setText(weatherModel.getWindSpeed());
        humidity.setText(weatherModel.getHumidity());
        visibility.setText(weatherModel.getVisibility());
        sunrise.setText(weatherModel.getSunrise());
        sunset.setText(weatherModel.getSunset());
    }
}