package com.ximsfei.skindemo;

import android.app.Application;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.circleimageview.app.SkinCircleImageViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        SkinCircleImageViewManager.init(this);
//        SkinMaterialManager.init(this);
//        SkinCardViewManager.init(this);
//        SkinCompatManager.init(this).loadSkin();
        SkinCompatManager.init(this)
                .addInflater(new SkinMaterialViewInflater())    // material design
                .addInflater(new SkinCardViewInflater())        // CardView v7
                .addInflater(new SkinCircleImageViewInflater()) // hdodenhof/CircleImageView
                .loadSkin();
    }
}
