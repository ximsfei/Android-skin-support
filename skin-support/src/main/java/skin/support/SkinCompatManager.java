package skin.support;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import skin.support.app.SkinLayoutInflater;
import skin.support.observe.SkinObservable;
import skin.support.utils.SkinConstants;
import skin.support.utils.SkinFileUtils;
import skin.support.utils.SkinLog;
import skin.support.utils.SkinPreference;
import skin.support.content.res.SkinCompatResources;

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

import skin.support.app.SkinCompatDelegate;
import skin.support.observe.SkinObserver;
import skin.support.widget.SkinCompatThemeUtils;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;
import static skin.support.widget.SkinCompatHelper.checkResourceId;

/**
 * Created by ximsfei on 17-1-10.
 */

public class SkinCompatManager extends SkinObservable {
    private static volatile SkinCompatManager sInstance;
    private final Object mLock = new Object();
    private final Context mAppContext;
    private boolean mLoading = false;
    private List<SkinLayoutInflater> mInflaters = new ArrayList<>();
    private List<SkinLayoutInflater> mHookInflaters = new ArrayList<>();
    private boolean mSkinStatusBarColorEnable = true;
    private boolean mSkinWindowBackgroundColorEnable = true;

    public interface SkinLoaderListener {
        void onStart();

        void onSuccess();

        void onFailed(String errMsg);
    }

    public static SkinCompatManager init(Context context) {
        if (sInstance == null) {
            synchronized (SkinCompatManager.class) {
                if (sInstance == null) {
                    sInstance = new SkinCompatManager(context);
                }
            }
        }
        return sInstance;
    }

    public static SkinCompatManager getInstance() {
        return sInstance;
    }

    public static SkinCompatManager withoutActivity(Application application) {
        if (sInstance == null)
            sInstance = init(application);
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {

            private WeakHashMap<Activity, SkinCompatDelegate> skinDeleMap;
            private WeakHashMap<Activity, SkinObserver> skinObserverMap;

            private SkinCompatDelegate getSkinDelegate(AppCompatActivity activity) {
                if (skinDeleMap == null) {
                    skinDeleMap = new WeakHashMap<>();
                }

                SkinCompatDelegate mSkinDelegate = skinDeleMap.get(activity);
                if (mSkinDelegate == null) {
                    mSkinDelegate = SkinCompatDelegate.create(activity);
                }
                skinDeleMap.put(activity, mSkinDelegate);
                return mSkinDelegate;
            }

            private SkinObserver getObserver(final Activity activity) {
                if (skinObserverMap == null) {
                    skinObserverMap = new WeakHashMap<>();
                }
                SkinObserver observer = skinObserverMap.get(activity);
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
                skinObserverMap.put(activity, observer);
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

            protected void updateWindowBackground(Activity activity) {
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
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
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
                skinObserverMap.remove(activity);
                skinDeleMap.remove(activity);
            }
        });
        return sInstance;
    }

    private SkinCompatManager(Context context) {
        mAppContext = context.getApplicationContext();
        SkinPreference.init(mAppContext);
        SkinCompatResources.init(mAppContext);
    }

    public SkinCompatManager addInflater(SkinLayoutInflater inflater) {
        mInflaters.add(inflater);
        return this;
    }

    public List<SkinLayoutInflater> getInflaters() {
        return mInflaters;
    }

    public SkinCompatManager addHookInflater(SkinLayoutInflater inflater) {
        mHookInflaters.add(inflater);
        return this;
    }

    public List<SkinLayoutInflater> getHookInflaters() {
        return mHookInflaters;
    }

    public String getCurSkinName() {
        return SkinPreference.getInstance().getSkinName();
    }

    public void restoreDefaultTheme() {
        loadSkin("");
    }

    private SkinCompatManager setSkinStatusBarColorEnable(boolean enable) {
        mSkinStatusBarColorEnable = enable;
        return this;
    }

    private boolean isSkinStatusBarColorEnable() {
        return mSkinStatusBarColorEnable;
    }

    private SkinCompatManager setSkinWindowBackgroundEnable(boolean enable) {
        mSkinWindowBackgroundColorEnable = enable;
        return this;
    }

    private boolean isSkinWindowBackgroundEnable() {
        return mSkinWindowBackgroundColorEnable;
    }

    public AsyncTask loadSkin() {
        String skin = SkinPreference.getInstance().getSkinName();
        if (TextUtils.isEmpty(skin)) {
            return null;
        }
        return loadSkin(skin, null);
    }

    public AsyncTask loadSkin(SkinLoaderListener listener) {
        String skin = SkinPreference.getInstance().getSkinName();
        if (TextUtils.isEmpty(skin)) {
            return null;
        }
        return loadSkin(skin, listener);
    }

    public AsyncTask loadSkin(String skinName) {
        return loadSkin(skinName, null);
    }

    public AsyncTask loadSkin(String skinName, final SkinLoaderListener listener) {
        return new SkinLoadTask(listener).execute(skinName);
    }

    private class SkinLoadTask extends AsyncTask<String, Void, String> {
        private final SkinLoaderListener mListener;

        public SkinLoadTask(SkinLoaderListener listener) {
            mListener = listener;
        }

        protected void onPreExecute() {
            if (mListener != null) {
                mListener.onStart();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            synchronized (mLock) {
                while (mLoading) {
                    try {
                        mLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mLoading = true;
            }
            try {
                if (params.length == 1) {
                    if (TextUtils.isEmpty(params[0])) {
                        SkinCompatResources.getInstance().setSkinResource(
                                mAppContext.getResources(), mAppContext.getPackageName());
                        return params[0];
                    }
                    SkinLog.d("skinPkgPath", params[0]);
                    String skinPkgPath = SkinFileUtils.getSkinDir(mAppContext) + File.separator + params[0];
                    // ToDo 方便调试, 每次点击都从assets中读取
//                    if (!isSkinExists(params[0])) {
                    copySkinFromAssets(params[0]);
                    if (!isSkinExists(params[0])) {
                        return null;
                    }
//                    }
                    String pkgName = initSkinPackageName(skinPkgPath);
                    Resources resources = initSkinResources(skinPkgPath);
                    if (resources != null && !TextUtils.isEmpty(pkgName)) {
                        SkinCompatResources.getInstance().setSkinResource(resources, pkgName);
                        return params[0];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            SkinCompatResources.getInstance().setSkinResource(
                    mAppContext.getResources(), mAppContext.getPackageName());
            return null;
        }

        protected void onPostExecute(String skinName) {
            SkinLog.e("skinName = " + skinName);
            synchronized (mLock) {
                if (skinName != null) {
                    notifyUpdateSkin();
                    SkinPreference.getInstance().setSkinName(skinName).commitEditor();
                    if (mListener != null) mListener.onSuccess();
                } else {
                    SkinPreference.getInstance().setSkinName("").commitEditor();
                    if (mListener != null) mListener.onFailed("皮肤资源获取失败");
                }
                mLoading = false;
                mLock.notifyAll();
            }
        }
    }

    private String initSkinPackageName(String skinPkgPath) {
        PackageManager mPm = mAppContext.getPackageManager();
        PackageInfo info = mPm.getPackageArchiveInfo(skinPkgPath, PackageManager.GET_ACTIVITIES);
        return info.packageName;
    }

    @Nullable
    private Resources initSkinResources(String skinPkgPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, skinPkgPath);

            Resources superRes = mAppContext.getResources();
            return new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String copySkinFromAssets(String name) {
        String skinDir = SkinFileUtils.getSkinDir(mAppContext);
        String skinPath = skinDir + File.separator + name;
        try {
            InputStream is = mAppContext.getAssets().open(
                    SkinConstants.SKIN_DEPLOY_PATH
                            + File.separator
                            + name);
            File fileDir = new File(skinDir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            OutputStream os = new FileOutputStream(skinPath);
            int byteCount;
            byte[] bytes = new byte[1024];

            while ((byteCount = is.read(bytes)) != -1) {
                os.write(bytes, 0, byteCount);
            }
            os.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return skinPath;
    }

    public boolean isSkinExists(String skinName) {
        return !TextUtils.isEmpty(skinName)
                && new File(SkinFileUtils.getSkinDir(mAppContext) + File.separator + skinName).exists();
    }
}