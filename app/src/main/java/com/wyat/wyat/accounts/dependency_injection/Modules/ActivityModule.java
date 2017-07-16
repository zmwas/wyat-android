package com.wyat.wyat.accounts.dependency_injection.Modules;

/**
 * Created by zack on 01/01/17.
 */

import android.content.Context;

import com.wyat.wyat.common.Activity;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {
    private final Context mContext;

    public ActivityModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Activity
    Context provideActivityContext() {
        return mContext;
    }
}