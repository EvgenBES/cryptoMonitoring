package com.test.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.test.com.testproject.R;

import com.facebook.stetho.Stetho;
import com.test.injection.AppComponent;
import com.test.injection.AppModule;
import com.test.injection.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {

    private static AppComponent appComponent;
    private static Context context;

    @Inject
    public DispatchingAndroidInjector<Activity> activityInjector;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Stetho.initializeWithDefaults(this);

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
