package com.wyat.wyat.accounts.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wyat.SharedPrefsWrapper;
import com.wyat.wyat.R;
import com.wyat.wyat.SharedPreferenceImpl;
import com.wyat.wyat.accounts.dependency_injection.Components.DaggerLoginComponent;
import com.wyat.wyat.accounts.dependency_injection.Modules.LoginModule;
import com.wyat.wyat.accounts.presenters.LoginPresenter;
import com.wyat.wyat.accounts.views.LoginView;
import com.wyat.wyat.application.AppComponent;
import com.wyat.wyat.application.WyatApplication;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.wyat.wyat.events.activities.EventListActivity;
import com.wyat.wyat.events.activities.PostEventActivity;

/**
 * Created by zack on 10/01/17.
 */

public class LoginActivity extends AppCompatActivity implements LoginView{
    @Bind(R.id.email_login)
    EditText email_edittext;
    @Bind(R.id.password_login)
    EditText password_edittext;
    @Bind(R.id.login_button)
    Button LoginButton;
    @Bind(R.id.create_account_prompt)
    TextView create_account_text;
    String email;
    String password;
    @Inject
    LoginPresenter loginPresenter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        AppComponent appComponent = ((WyatApplication) getApplication()).getAppComponent();
        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule())
                .build()
                .inject(this);

        loginPresenter.attachView(this);


    }


    @OnClick(R.id.login_button)
    public void loginClicked() {
        email = email_edittext.getText().toString();
        password = password_edittext.getText().toString();
        loginPresenter.setEmail(email);
        loginPresenter.setPassword(password);
        loginPresenter.login();
    }

    @OnClick(R.id.create_account_prompt)
    public void signup_prompt_clicked() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public static void start(Context context){
        Intent loginIntent = new Intent(context,LoginActivity.class);
        context.startActivity(loginIntent);

    }

    @Override
    public void showMainScreen() {
        Intent intent = new Intent(this, EventListActivity.class);
        startActivity(intent);
    }

    @Override
    public void saveToken() {

    }
}
