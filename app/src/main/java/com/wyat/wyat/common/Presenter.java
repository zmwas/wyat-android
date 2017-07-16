package com.wyat.wyat.common;

/**
 * Created by zack on 01/01/17.
 */

import com.wyat.wyat.common.View;

public interface Presenter<T extends View> {

    void onStart();

    void onStop();

    void onPause();

    void attachView (T view);

    void onCreate();


}
