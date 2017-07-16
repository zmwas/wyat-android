package com.wyat.wyat.application;

import com.wyat.SharedPrefsWrapper;
import com.wyat.repository.WyatRepository;
import com.wyat.rest.RestDataSource;
import com.wyat.wyat.SharedPreferenceImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zack on 01/01/17.
 */

@Singleton @Component(modules = AppModule.class)
public interface AppComponent {

    WyatApplication wyatapplication();

    WyatRepository wyatrepository();

    SharedPrefsWrapper sharedPreference();





}
