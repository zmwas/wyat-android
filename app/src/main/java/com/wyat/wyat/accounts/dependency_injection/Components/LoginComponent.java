package com.wyat.wyat.accounts.dependency_injection.Components;

import com.wyat.GetAccessTokenUseCase;
import com.wyat.wyat.accounts.activities.LoginActivity;
import com.wyat.wyat.common.Activity;
import com.wyat.wyat.accounts.dependency_injection.Modules.ActivityModule;
import com.wyat.wyat.accounts.dependency_injection.Modules.LoginModule;
import com.wyat.wyat.application.AppComponent;

import dagger.Component;

/**
 * Created by zack on 12/01/17.
 */

@Activity @Component(modules = {LoginModule.class, ActivityModule.class},dependencies = {AppComponent.class})
public interface LoginComponent {
    GetAccessTokenUseCase getAccessToken();

    void inject(LoginActivity loginActivity);



}
