package com.afei.skinzipdemo;

import android.app.Application;

import com.afei.skinzipdemo.skin.ZipSDCardLoader;

import skin.support.SkinCompatManager;

/**
 * Created by mac on 2018/7/18.
 */

public class App extends Application {

    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SkinCompatManager.withoutActivity(this)
                .addStrategy(new ZipSDCardLoader())
                .loadSkin();
    }
}
