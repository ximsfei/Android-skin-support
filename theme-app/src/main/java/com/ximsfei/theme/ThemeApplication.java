package com.ximsfei.theme;

import android.app.Application;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * Created by pengfengwang on 2017/3/11.
 */

public class ThemeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinCompatManager.init(this)
                .addInflater(new SkinMaterialViewInflater())
                .addInflater(new SkinCardViewInflater())
                .loadSkin();
    }
}
