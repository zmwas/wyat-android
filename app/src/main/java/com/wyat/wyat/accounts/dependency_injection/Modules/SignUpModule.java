package com.wyat.wyat.accounts.dependency_injection.Modules;

import com.wyat.PostUserUseCase;
import com.wyat.repository.WyatRepository;
import com.wyat.wyat.accounts.presenters.SignUpPresenter;
import com.wyat.wyat.common.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zack on 01/01/17.
 */

@Module
public class SignUpModule {
    @Provides @Activity
    PostUserUseCase providesPostUserUseCase(WyatRepository wyatRepository){
        return new PostUserUseCase(wyatRepository);
    }


    @Provides @Activity
    SignUpPresenter providesSignUpPresenter(PostUserUseCase postUserUseCase){

        return new SignUpPresenter(postUserUseCase);
    }

}
