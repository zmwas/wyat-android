package com.wyat.wyat.application;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.wyat.SharedPrefsWrapper;
import com.wyat.repository.WyatRepository;
import com.wyat.rest.RestDataSource;
import com.wyat.wyat.SharedPreferenceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zack on 01/01/17.
 */

@Module
public class AppModule {

    private final WyatApplication wyatApplication;

    public AppModule(WyatApplication wyatApplication) {
        this.wyatApplication = wyatApplication;
    }

    @Provides
    @Singleton

    WyatApplication providesWyatApplication(){
        return wyatApplication;
    }


    @Provides @Singleton
    SharedPreferenceImpl providesSharedPreference(WyatApplication wyatApplication){
        return new SharedPreferenceImpl(wyatApplication);
    }

    @Provides @Singleton
    SharedPrefsWrapper providesSharedPrefsWrapper(SharedPreferenceImpl sharedPreference){
        return  sharedPreference;
    }

    @Provides @Singleton
    RestDataSource providesRestDataSource(SharedPrefsWrapper sharedPrefsWrapper){
        return new RestDataSource(sharedPrefsWrapper);
    }


    @Provides
    @Singleton
    WyatRepository providesWyatRepository(RestDataSource restDataSource){
        return restDataSource;
    }


}
