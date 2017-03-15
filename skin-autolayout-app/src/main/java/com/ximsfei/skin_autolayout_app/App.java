package com.ximsfei.skin_autolayout_app;

import android.app.Application;

import skin.support.SkinCompatManager;
import skin.support.app.SkinHookAutoLayoutViewInflater;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        SkinHookAutoLayoutManager.init(this);
        SkinCompatManager.init(this)
                .addHookInflater(new SkinHookAutoLayoutViewInflater())
                .loadSkin();
    }
}
