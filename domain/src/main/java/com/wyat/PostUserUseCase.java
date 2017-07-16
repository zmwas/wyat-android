package com.wyat;

import com.wyat.entities.User;
import com.wyat.repository.WyatRepository;


import rx.Observable;

/**
 * Created by zack on 01/01/17.
 */

public class PostUserUseCase extends UseCase<User> {

    private WyatRepository wyatRepository;
    private User wyat_user;
    private String authtoken;

    public PostUserUseCase(WyatRepository repository) {
       wyatRepository = repository;

    }

    public void setWyat_user(User user){

        wyat_user = user;
    }



    @Override
    public Observable<User> buildObservable() {
        return wyatRepository.postUser(wyat_user);
    }
}
