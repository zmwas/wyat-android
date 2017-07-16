package com.wyat;

/**
 * Created by zack on 16/01/17.
 */

public interface SharedPrefsWrapper {

    void putBoolean(String key, boolean value);

    boolean getBoolean(String key);

    void putString(String key, String value);

    String getString(String key);

    void putInt(String key, int value);

    int getInt(String  key);

    void putFloat(String key, float value);

    float getFloat(String key);

    void putLong(String key, long value);

    long getLong(String key);


}
