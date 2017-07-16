package com.wyat.wyat.accounts.presenters;


import android.util.Log;

import com.wyat.PostUserUseCase;
import com.wyat.entities.User;
import com.wyat.wyat.accounts.views.SignUpView;
import com.wyat.wyat.common.Presenter;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by zack on 01/01/17.
 */

public class SignUpPresenter implements Presenter<SignUpView> {
    private PostUserUseCase postUserUseCase;

    private User user;

    private Subscription subscription;

    private SignUpView signUpView;

    public SignUpPresenter(PostUserUseCase postUserUseCase) {
        this.postUserUseCase = postUserUseCase;
        this.user = new User();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(SignUpView view) {

        this.signUpView = view;
    }


    @Override
    public void onCreate() {


    }

    public void setEmail(String email) {
        user.setEmail(email);
    }

    public void setPassword(String password) {
        user.setPassword(password);
    }

    public void setFirstname(String firstname) {
        user.setFirstName(firstname);
    }

    public void setLastname(String lastname) {
        user.setLastName(lastname);
    }


    public void signUp() {
        postUserUseCase.setWyat_user(user);

        subscription = postUserUseCase
                .execute()
                .subscribeOn(rx.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        Log.d("complete","complete");
                        signUpView.showLoginScreen();


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {
                        Log.d("next",user.getEmail());

                    }
                });
    }
}
