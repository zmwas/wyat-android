package com.wyat.wyat.events.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wyat.wyat.R;
import com.wyat.wyat.events.views.PostEventView;

/**
 * Created by zack on 22/01/17.
 */

public class PostEventActivity extends AppCompatActivity implements PostEventView{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_layout);




    }
}
