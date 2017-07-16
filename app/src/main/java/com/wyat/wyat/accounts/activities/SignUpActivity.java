package com.wyat.wyat.accounts.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.wyat.entities.User;
import com.wyat.wyat.R;
import com.wyat.wyat.accounts.dependency_injection.Components.DaggerSignUpComponent;
import com.wyat.wyat.accounts.dependency_injection.Modules.SignUpModule;
import com.wyat.wyat.accounts.presenters.LoginPresenter;
import com.wyat.wyat.accounts.presenters.SignUpPresenter;
import com.wyat.wyat.accounts.views.SignUpView;
import com.wyat.wyat.application.AppComponent;
import com.wyat.wyat.application.WyatApplication;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zack on 01/01/17.
 */

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    @Bind(R.id.email_sign_up)
    EditText email_edit_text;
    @Bind(R.id.first_name_sign_up)
    EditText first_name;
    @Bind(R.id.last_name_sign_up)
    EditText last_name;
    @Bind(R.id.password_sign_up)
    EditText pass_word;
    @Bind(R.id.confirm_password_sign_up)
    EditText password_confirmation;
    @Bind(R.id.button_sign_up)
    Button signup_button;

    @Inject
    SignUpPresenter signupPresenter;

    User user;

    String email;
    String firstname;
    String lastname;
    String password;

    @Override
    protected void onStop() {
        super.onStop();

        signupPresenter.onStop();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        ButterKnife.bind(this);

        AppComponent appComponent = ((WyatApplication)getApplication()).getAppComponent();



        DaggerSignUpComponent
                .builder()
                .appComponent(appComponent)
                .signUpModule(new SignUpModule())
                .build()
                .inject(this);
        signupPresenter.attachView(this);

    }
    @OnClick(R.id.button_sign_up)
    public void onClick() {



        email = email_edit_text.getText().toString();
        Log.v("message",email);
        firstname = first_name.getText().toString();
        lastname =last_name.getText().toString();
        password = pass_word.getText().toString();

        signupPresenter.setEmail(email);
        signupPresenter.setFirstname(firstname);
        signupPresenter.setLastname(lastname);
        signupPresenter.setPassword(password);
        signupPresenter.signUp();

    }


    @Override
    public void showLoginScreen() {

        Intent loginIntent = new Intent(this,LoginActivity.class);
        startActivity(loginIntent);

    }
}
