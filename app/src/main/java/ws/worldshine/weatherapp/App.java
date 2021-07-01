package ws.worldshine.weatherapp;

import android.app.Application;

import org.koin.android.java.KoinAndroidApplication;
import org.koin.core.KoinApplication;

import static org.koin.core.context.DefaultContextExtKt.startKoin;
import static ws.worldshine.weatherapp.di.AppModuleKt.getAppModule;
import static ws.worldshine.weatherapp.di.DatabaseModuleKt.getDatabaseModule;
import static ws.worldshine.weatherapp.di.NetworkModuleKt.getNetworkModule;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        KoinApplication koin = KoinAndroidApplication.create(this)
                .modules(getAppModule(),
                        getNetworkModule(),
                        getDatabaseModule());
        startKoin(koin);
    }

}
