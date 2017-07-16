package com.wyat.wyat.accounts.dependency_injection.Components;

import com.wyat.PostUserUseCase;
import com.wyat.wyat.accounts.activities.SignUpActivity;
import com.wyat.wyat.common.Activity;
import com.wyat.wyat.accounts.dependency_injection.Modules.ActivityModule;
import com.wyat.wyat.accounts.dependency_injection.Modules.SignUpModule;
import com.wyat.wyat.application.AppComponent;

import dagger.Component;


/**
 * Created by zack on 01/01/17.
 */
@Activity  @Component(modules = {SignUpModule.class, ActivityModule.class},dependencies = {AppComponent.class})
public interface SignUpComponent {
    PostUserUseCase postUserUseCase();

    void inject(SignUpActivity signUpActivity);



}
