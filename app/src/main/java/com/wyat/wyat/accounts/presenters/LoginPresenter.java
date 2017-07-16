package com.wyat.wyat.accounts.presenters;

import android.util.Log;

import com.wyat.GetAccessTokenUseCase;
import com.wyat.SharedPrefsWrapper;
import com.wyat.entities.AccessToken;
import com.wyat.wyat.accounts.views.LoginView;
import com.wyat.wyat.common.Presenter;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zack on 12/01/17.
 */

public class LoginPresenter implements Presenter<LoginView> {

    private GetAccessTokenUseCase getAccessTokenUseCase;
    private Subscription subscription;
    private String email;
    private String password;
    private SharedPrefsWrapper sharedPrefsWrapper;


    LoginView loginView;

    public LoginPresenter(GetAccessTokenUseCase getAccessTokenUseCase, SharedPrefsWrapper sharedPrefsWrapper) {
        this.getAccessTokenUseCase = getAccessTokenUseCase;
        this.sharedPrefsWrapper = sharedPrefsWrapper;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

        if (!subscription.isUnsubscribed()&&subscription!=null){
            subscription.unsubscribe();
        }


    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(LoginView view) {
        this.loginView = view;
    }

    @Override
    public void onCreate() {


    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void login() {
        getAccessTokenUseCase.setEmail(email);
        getAccessTokenUseCase.setPassword(password);

        subscription = getAccessTokenUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AccessToken>() {
                    @Override
                    public void onCompleted() {

                        loginView.showMainScreen();
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d("error", e.getMessage());

                    }

                    @Override
                    public void onNext(AccessToken accessToken) {

                        sharedPrefsWrapper.putString("token", accessToken.getAccessToken());
                        Log.v("TAG", sharedPrefsWrapper.getString("token"));

                    }
                });

    }
}
