package ws.worldshine.weatherapp.screens.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import kotlinx.datetime.Clock;
import kotlinx.datetime.ClockKt;
import kotlinx.datetime.Instant;
import kotlinx.datetime.LocalDateTime;
import ws.worldshine.weatherapp.R;

import static org.koin.java.KoinJavaComponent.get;

public class MainFragment extends Fragment {

    private final MainFragmentViewModel viewModel = get(MainFragmentViewModel.class);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}