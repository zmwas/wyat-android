package com.wyat.wyat;

import android.content.Context;
import android.content.SharedPreferences;

import com.wyat.SharedPrefsWrapper;

/**
 * Created by zack on 02/02/17.
 */

public class SharedPreferenceImpl implements SharedPrefsWrapper {

    private SharedPreferences sharedPreferences;

    private Context context;
    private static final String PREFS= "Wyat_prefs";

    public SharedPreferenceImpl(Context context) {
        this.context = context;
    }

    @Override
    public void putBoolean(String key, boolean value) {

    }

    @Override
    public boolean getBoolean(String key) {
        return false;
    }

    @Override
    public void putString(String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS,0).edit();
        editor.putString(key,value);
        editor.apply();

    }

    @Override
    public String getString(String key) {

       sharedPreferences = context.getSharedPreferences(PREFS,0);


        return sharedPreferences.getString(key,null);
    }

    @Override
    public void putInt(String key, int value) {

    }

    @Override
    public int getInt(String key) {
        return 0;
    }

    @Override
    public void putFloat(String key, float value) {

    }

    @Override
    public float getFloat(String key) {
        return 0;
    }

    @Override
    public void putLong(String key, long value) {

    }

    @Override
    public long getLong(String key) {
        return 0;
    }
}
