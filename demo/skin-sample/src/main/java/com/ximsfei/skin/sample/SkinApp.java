package com.ximsfei.skin.sample;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.ximsfei.skin.sample.loader.CustomSDCardLoader;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;
import skin.support.utils.Slog;

public class SkinApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Slog.DEBUG = true;
        SkinCompatManager.withoutActivity(this)
                // 自定义加载策略，指定SDCard路径
                .addStrategy(new CustomSDCardLoader())
                // material design
                .addInflater(new SkinMaterialViewInflater())
                // ConstraintLayout
                .addInflater(new SkinConstraintViewInflater())
                // CardView v7
                .addInflater(new SkinCardViewInflater())
                // 关闭状态栏换肤
                .setSkinStatusBarColorEnable(false)
                // 关闭windowBackground换肤
                .setSkinWindowBackgroundEnable(false)
//                .setSkinAllActivityEnable(false)                // true: 默认所有的Activity都换肤; false: 只有实现SkinCompatSupportable接口的Activity换肤
                .loadSkin();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
