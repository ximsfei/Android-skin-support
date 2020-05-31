package skin.support.mobile.demo;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import skin.support.SkinCompatManager;
import skin.support.app.SkinAppCompatViewInflater;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;
import skin.support.utils.Slog;

public class SkinApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Slog.DEBUG = BuildConfig.DEBUG;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        SkinCompatManager.withoutActivity(this)
//                .addStrategy(new CustomSDCardLoader())          // 自定义加载策略，指定SDCard路径
//                .addStrategy(new ZipSDCardLoader())             // 自定义加载策略，获取zip包中的资源
                .addInflater(new SkinAppCompatViewInflater())   // 基础控件换肤
                .addInflater(new SkinMaterialViewInflater())    // material design
                .addInflater(new SkinConstraintViewInflater())  // ConstraintLayout
                .addInflater(new SkinCardViewInflater())        // CardView v7
//                .setSkinStatusBarColorEnable(true)              // 关闭状态栏换肤
//                .setSkinWindowBackgroundEnable(false)           // 关闭windowBackground换肤
//                .setSkinAllActivityEnable(false)                // true: 默认所有的Activity都换肤; false: 只有实现SkinCompatSupportable接口的Activity换肤
                .loadSkin();
    }
}
