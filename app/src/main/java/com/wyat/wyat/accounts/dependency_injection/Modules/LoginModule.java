package com.wyat.wyat.accounts.dependency_injection.Modules;

import com.wyat.GetAccessTokenUseCase;
import com.wyat.SharedPrefsWrapper;
import com.wyat.repository.WyatRepository;
import com.wyat.wyat.common.Activity;
import com.wyat.wyat.accounts.presenters.LoginPresenter;


import dagger.Module;
import dagger.Provides;

/**
 * Created by zack on 12/01/17.
 */
@Module()
public class LoginModule {


    @Provides @Activity
    GetAccessTokenUseCase providesGetAccessTokenUseCase(WyatRepository wyatRepository){
        return new GetAccessTokenUseCase(wyatRepository);
    }

    @Provides @Activity
    LoginPresenter providesLoginPresenter(GetAccessTokenUseCase getAccessTokenUseCase,SharedPrefsWrapper sharedPrefsWrapper){
        return new LoginPresenter(getAccessTokenUseCase,sharedPrefsWrapper);
    }



}
