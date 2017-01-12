package com.ximsfei.dynamicskindemo;

import android.app.Application;

import skin.support.SkinCompatManager;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinCompatManager.init(this)
                .loadSkin();
    }
}
