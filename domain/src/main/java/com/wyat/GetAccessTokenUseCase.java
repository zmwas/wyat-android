package com.wyat;

import com.wyat.entities.AccessToken;
import com.wyat.repository.WyatRepository;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by zack on 12/01/17.
 */

public class GetAccessTokenUseCase extends UseCase<AccessToken> {

    WyatRepository wyatRepository;
    String email,password;

    public GetAccessTokenUseCase(WyatRepository repository) {
        wyatRepository =repository;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Observable buildObservable() {
        return wyatRepository.getAccessToken(email,password);
    }




}
