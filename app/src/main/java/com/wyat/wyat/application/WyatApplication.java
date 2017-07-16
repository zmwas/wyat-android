package com.wyat.wyat.application;

import android.app.Application;

/**
 * Created by zack on 01/01/17.
 */

public class WyatApplication extends Application{

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }


    public AppComponent getAppComponent(){
        return appComponent;
    }

    private void initializeInjector(){
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
}
