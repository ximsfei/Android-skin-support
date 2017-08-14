package skin.support.app;

import android.app.Activity;
import android.app.Application;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import java.lang.reflect.Field;
import java.util.WeakHashMap;

import skin.support.SkinCompatManager;
import skin.support.content.res.SkinCompatResources;
import skin.support.observe.SkinObservable;
import skin.support.observe.SkinObserver;
import skin.support.widget.SkinCompatThemeUtils;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;
import static skin.support.widget.SkinCompatHelper.checkResourceId;

public class SkinActivityLifecycle implements Application.ActivityLifecycleCallbacks {
    private static volatile SkinActivityLifecycle sInstance = null;
    private WeakHashMap<Activity, SkinCompatDelegate> mSkinDelegateMap;
    private WeakHashMap<Activity, SkinObserver> mSkinObserverMap;

    public static SkinActivityLifecycle init(Application application) {
        if (sInstance == null) {
            synchronized (SkinActivityLifecycle.class) {
                if (sInstance == null) {
                    sInstance = new SkinActivityLifecycle(application);
                }
            }
        }
        return sInstance;
    }

    private SkinActivityLifecycle(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

    private SkinCompatDelegate getSkinDelegate(AppCompatActivity activity) {
        if (mSkinDelegateMap == null) {
            mSkinDelegateMap = new WeakHashMap<>();
        }

        SkinCompatDelegate mSkinDelegate = mSkinDelegateMap.get(activity);
        if (mSkinDelegate == null) {
            mSkinDelegate = SkinCompatDelegate.create(activity);
        }
        mSkinDelegateMap.put(activity, mSkinDelegate);
        return mSkinDelegate;
    }

    private SkinObserver getObserver(final Activity activity) {
        if (mSkinObserverMap == null) {
            mSkinObserverMap = new WeakHashMap<>();
        }
        SkinObserver observer = mSkinObserverMap.get(activity);
        if (observer == null) {
            observer = new SkinObserver() {
                @Override
                public void updateSkin(SkinObservable observable, Object o) {
                    updateStatusBarColor(activity);
                    updateWindowBackground(activity);
                    getSkinDelegate((AppCompatActivity) activity).applySkin();
                }
            };
        }
        mSkinObserverMap.put(activity, observer);
        return observer;
    }

    private void updateStatusBarColor(Activity activity) {
        if (SkinCompatManager.getInstance().isSkinStatusBarColorEnable()
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int statusBarColorResId = SkinCompatThemeUtils.getStatusBarColorResId(activity);
            int colorPrimaryDarkResId = SkinCompatThemeUtils.getColorPrimaryDarkResId(activity);
            if (checkResourceId(statusBarColorResId) != INVALID_ID) {
                activity.getWindow().setStatusBarColor(SkinCompatResources.getInstance().getColor(statusBarColorResId));
            } else if (checkResourceId(colorPrimaryDarkResId) != INVALID_ID) {
                activity.getWindow().setStatusBarColor(SkinCompatResources.getInstance().getColor(colorPrimaryDarkResId));
            }
        }
    }

    private void updateWindowBackground(Activity activity) {
        if (SkinCompatManager.getInstance().isSkinWindowBackgroundEnable()) {
            int windowBackgroundResId = SkinCompatThemeUtils.getWindowBackgroundResId(activity);
            if (checkResourceId(windowBackgroundResId) != INVALID_ID) {
                String typeName = activity.getResources().getResourceTypeName(windowBackgroundResId);
                if ("color".equals(typeName)) {
                    Drawable drawable = new ColorDrawable(SkinCompatResources.getInstance().getColor(windowBackgroundResId));
                    activity.getWindow().setBackgroundDrawable(drawable);
                } else if ("drawable".equals(typeName)) {
                    Drawable drawable = SkinCompatResources.getInstance().getDrawable(windowBackgroundResId);
                    activity.getWindow().setBackgroundDrawable(drawable);
                } else if ("mipmap".equals(typeName)) {
                    Drawable drawable = SkinCompatResources.getInstance().getMipmap(windowBackgroundResId);
                    activity.getWindow().setBackgroundDrawable(drawable);
                }
            }
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activity instanceof AppCompatActivity) {
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            try {
                Field field = LayoutInflater.class.getDeclaredField("mFactorySet");
                field.setAccessible(true);
                field.setBoolean(layoutInflater, false);
                LayoutInflaterCompat.setFactory(activity.getLayoutInflater(),
                        getSkinDelegate((AppCompatActivity) activity));
            } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            updateStatusBarColor(activity);
            updateWindowBackground(activity);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        SkinCompatManager.getInstance().addObserver(getObserver(activity));
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        SkinCompatManager.getInstance().deleteObserver(getObserver(activity));
        mSkinObserverMap.remove(activity);
        mSkinDelegateMap.remove(activity);
    }
}