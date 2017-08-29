package com.ximsfei.skindemo;

import android.app.Application;

import com.ximsfei.skindemo.loader.CustomSDCardLoader;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.circleimageview.app.SkinCircleImageViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;
import skin.support.flycotablayout.app.SkinFlycoTabLayoutInflater;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        SkinCircleImageViewManager.init(this);
//        SkinMaterialManager.init(this);
//        SkinConstraintManager.init(this);
//        SkinCardViewManager.init(this);
//        SkinFlycoTabLayoutManager.init(this);
//        SkinCompatManager.init(this).loadSkin();
//        SkinCompatManager.init(this)
        SkinCompatManager.withoutActivity(this)
                .addStrategy(new CustomSDCardLoader())          // 自定义加载策略，指定SDCard路径
                .addInflater(new SkinMaterialViewInflater())    // material design
                .addInflater(new SkinConstraintViewInflater())  // ConstraintLayout
                .addInflater(new SkinCardViewInflater())        // CardView v7
                .addInflater(new SkinCircleImageViewInflater()) // hdodenhof/CircleImageView
                .addInflater(new SkinFlycoTabLayoutInflater())  // H07000223/FlycoTabLayout
//                .setSkinStatusBarColorEnable(false)             // 关闭状态栏换肤
//                .setSkinWindowBackgroundEnable(false)           // 关闭windowBackground换肤
                .loadSkin();
    }
}
