package skin.support.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import skin.support.SkinCompatManager;
import skin.support.content.res.SkinCompatResources;
import skin.support.observe.SkinObservable;
import skin.support.observe.SkinObserver;
import skin.support.widget.SkinCompatThemeUtils;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;
import static skin.support.widget.SkinCompatHelper.checkResourceId;

public class SkinActivityLifecycle implements Application.ActivityLifecycleCallbacks {
    private static final Map<Context, SkinActivityLifecycle> sInstanceMap = new HashMap<>();
    private WeakHashMap<Activity, SkinCompatDelegate> mSkinDelegateMap;
    private WeakHashMap<Activity, SkinObserver> mSkinObserverMap;

    public static SkinActivityLifecycle init(Application application) {
        SkinActivityLifecycle instance = sInstanceMap.get(application);
        if (instance == null) {
            synchronized (SkinActivityLifecycle.class) {
                instance = sInstanceMap.get(application);
                if (instance == null) {
                    instance = new SkinActivityLifecycle(application);
                    sInstanceMap.put(application, instance);
                }
            }
        }
        return instance;
    }

    private SkinActivityLifecycle(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

    private SkinCompatDelegate getSkinDelegate(Activity activity) {
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
                    getSkinDelegate(activity).applySkin();
                    if (activity instanceof SkinActivity) {
                        updateWindowBackground(activity);
                        ((SkinActivity) activity).applySkin();
                    }
                }
            };
        }
        mSkinObserverMap.put(activity, observer);
        return observer;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (isActivitySkinEnable(activity)) {
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            try {
                Field field = LayoutInflater.class.getDeclaredField("mFactorySet");
                field.setAccessible(true);
                field.setBoolean(layoutInflater, false);
                LayoutInflaterCompat.setFactory(activity.getLayoutInflater(),
                        getSkinDelegate(activity));
            } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            updateWindowBackground(activity);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (isActivitySkinEnable(activity)) {
            SkinCompatManager.getInstance(activity).addObserver(getObserver(activity));
        }
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
        if (isActivitySkinEnable(activity)) {
            SkinCompatManager.getInstance(activity).deleteObserver(getObserver(activity));
            mSkinObserverMap.remove(activity);
            mSkinDelegateMap.remove(activity);
        }
    }

    private boolean isActivitySkinEnable(Activity activity) {
        return SkinCompatManager.getInstance(activity).isSkinAllActivityEnable() || activity instanceof SkinActivity;
    }

    private void updateWindowBackground(Activity activity) {
        if (SkinCompatManager.getInstance(activity).isSkinWindowBackgroundEnable()) {
            int windowBackgroundResId = SkinCompatThemeUtils.getWindowBackgroundResId(activity);
            if (checkResourceId(windowBackgroundResId) != INVALID_ID) {
                Drawable drawable = SkinCompatResources.getInstance(activity).getDrawable(windowBackgroundResId);
                if (drawable != null) {
                    activity.getWindow().setBackgroundDrawable(drawable);
                }
            }
        }
    }
}