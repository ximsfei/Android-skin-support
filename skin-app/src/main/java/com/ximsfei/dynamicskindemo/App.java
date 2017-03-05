package com.ximsfei.dynamicskindemo;

import android.app.Application;

import skin.support.SkinCardViewManager;
import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.design.SkinMaterialManager;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinCompatManager.init(this)
                .addInflater(new SkinMaterialViewInflater())
                .addInflater(new SkinCardViewInflater())
                .loadSkin();
    }
}
