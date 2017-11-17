package skin.support;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.SparseArray;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import skin.support.app.SkinActivityLifecycle;
import skin.support.app.SkinLayoutInflater;
import skin.support.load.SkinAssetsLoader;
import skin.support.load.SkinBuildInLoader;
import skin.support.load.SkinPrefixBuildInLoader;
import skin.support.observe.SkinObservable;
import skin.support.utils.SkinPreference;
import skin.support.content.res.SkinCompatResources;

public class SkinCompatManager extends SkinObservable {
    public static final int SKIN_LOADER_STRATEGY_NONE = -1;
    public static final int SKIN_LOADER_STRATEGY_ASSETS = 0;
    public static final int SKIN_LOADER_STRATEGY_BUILD_IN = 1;
    public static final int SKIN_LOADER_STRATEGY_PREFIX_BUILD_IN = 2;
    private static volatile SkinCompatManager sManager = null;
    private final Object mLock = new Object();
    private final Context mAppContext;
    private boolean mLoading = false;
    private List<SkinLayoutInflater> mInflaters = new ArrayList<>();
    private List<SkinLayoutInflater> mHookInflaters = new ArrayList<>();
    private SparseArray<SkinLoaderStrategy> mStrategyMap = new SparseArray<>();
    private boolean mSkinAllActivityEnable = true;
    private boolean mSkinWindowBackgroundColorEnable = false;

    /**
     * 皮肤包加载监听.
     */
    public interface SkinLoaderListener {
        /**
         * 开始加载.
         */
        void onStart();

        /**
         * 加载成功.
         */
        void onSuccess();

        /**
         * 加载失败.
         *
         * @param errMsg 错误信息.
         */
        void onFailed(String errMsg);
    }

    /**
     * 皮肤包加载策略.
     */
    public interface SkinLoaderStrategy {
        /**
         * 加载皮肤包.
         *
         * @param context  {@link Context}
         * @param skinName 皮肤包名称.
         * @return 加载成功，返回皮肤包名称；失败，则返回空。
         */
        String loadSkinInBackground(Context context, String skinName);

        /**
         * 根据应用中的资源ID，获取皮肤包相应资源的资源名.
         *
         * @param context  {@link Context}
         * @param skinName 皮肤包名称.
         * @param resId    应用中需要换肤的资源ID.
         * @return 皮肤包中相应的资源名.
         */
        String getTargetResourceEntryName(Context context, String skinName, int resId);

        /**
         * {@link #SKIN_LOADER_STRATEGY_ASSETS}
         * {@link #SKIN_LOADER_STRATEGY_BUILD_IN}
         * {@link #SKIN_LOADER_STRATEGY_PREFIX_BUILD_IN}
         *
         * @return 皮肤包加载策略类型.
         */
        int getType();
    }

    /**
     * 初始化换肤框架.
     *
     * @param application
     * @return
     */
    public static SkinCompatManager init(Application application) {
        if (sManager == null) {
            synchronized (SkinCompatManager.class) {
                sManager = new SkinCompatManager(application);
            }
        }
        return sManager;
    }

    public static SkinCompatManager getInstance() {
        return sManager;
    }

    private SkinCompatManager(Application application) {
        mAppContext = application;
        SkinPreference.init(mAppContext);
        SkinCompatResources.init(mAppContext);
        SkinActivityLifecycle.init(application);
        initLoaderStrategy();
    }

    private void initLoaderStrategy() {
        mStrategyMap.put(SKIN_LOADER_STRATEGY_ASSETS, new SkinAssetsLoader());
        mStrategyMap.put(SKIN_LOADER_STRATEGY_BUILD_IN, new SkinBuildInLoader());
        mStrategyMap.put(SKIN_LOADER_STRATEGY_PREFIX_BUILD_IN, new SkinPrefixBuildInLoader());
    }

    /**
     * 添加皮肤包加载策略.
     *
     * @param strategy 自定义加载策略
     * @return
     */
    public SkinCompatManager addStrategy(SkinLoaderStrategy strategy) {
        mStrategyMap.put(strategy.getType(), strategy);
        return this;
    }

    public SparseArray<SkinLoaderStrategy> getStrategies() {
        return mStrategyMap;
    }

    /**
     * 自定义View换肤时，可选择添加一个{@link SkinLayoutInflater}
     *
     * @param inflater 在{@link skin.support.app.SkinCompatViewInflater#createView(Context, String, String)}方法中调用.
     * @return
     */
    public SkinCompatManager addInflater(SkinLayoutInflater inflater) {
        mInflaters.add(inflater);
        return this;
    }

    public List<SkinLayoutInflater> getInflaters() {
        return mInflaters;
    }


    /**
     * 自定义View换肤时，可选择添加一个{@link SkinLayoutInflater}
     *
     * @param inflater 在{@link skin.support.app.SkinCompatViewInflater#createView(Context, String, String)}方法中最先调用.
     * @return
     */
    public SkinCompatManager addHookInflater(SkinLayoutInflater inflater) {
        mHookInflaters.add(inflater);
        return this;
    }

    public List<SkinLayoutInflater> getHookInflaters() {
        return mHookInflaters;
    }

    /**
     * 获取当前皮肤包.
     *
     * @return
     */
    public String getCurSkinName() {
        return SkinPreference.getInstance().getSkinName();
    }

    /**
     * 恢复默认主题，使用应用自带资源.
     */
    public void restoreDefaultTheme() {
        loadSkin("");
    }

    /**
     * 设置是否所有Activity都换肤.
     *
     * @param enable true: 所有Activity都换肤; false: 实现SkinCompatSupportable的Activity支持换肤.
     * @return
     */
    public SkinCompatManager setSkinAllActivityEnable(boolean enable) {
        mSkinAllActivityEnable = enable;
        return this;
    }

    public boolean isSkinAllActivityEnable() {
        return mSkinAllActivityEnable;
    }

    /**
     * 设置WindowBackground换肤，使用Theme中的{@link android.R.attr#windowBackground}属性.
     *
     * @param enable true: 打开; false: 关闭.
     * @return
     */
    public SkinCompatManager setSkinWindowBackgroundEnable(boolean enable) {
        mSkinWindowBackgroundColorEnable = enable;
        return this;
    }

    public boolean isSkinWindowBackgroundEnable() {
        return mSkinWindowBackgroundColorEnable;
    }

    /**
     * 加载记录的皮肤包，一般在Application中初始化换肤框架后调用.
     *
     * @return
     */
    public AsyncTask loadSkin() {
        String skin = SkinPreference.getInstance().getSkinName();
        int strategy = SkinPreference.getInstance().getSkinStrategy();
        if (TextUtils.isEmpty(skin) || strategy == SKIN_LOADER_STRATEGY_NONE) {
            return null;
        }
        return loadSkin(skin, null, strategy);
    }

    /**
     * 加载记录的皮肤包，一般在Application中初始化换肤框架后调用.
     *
     * @param listener 皮肤包加载监听.
     * @return
     */
    public AsyncTask loadSkin(SkinLoaderListener listener) {
        String skin = SkinPreference.getInstance().getSkinName();
        int strategy = SkinPreference.getInstance().getSkinStrategy();
        if (TextUtils.isEmpty(skin) || strategy == SKIN_LOADER_STRATEGY_NONE) {
            return null;
        }
        return loadSkin(skin, listener, strategy);
    }

    @Deprecated
    public AsyncTask loadSkin(String skinName) {
        return loadSkin(skinName, null);
    }

    @Deprecated
    public AsyncTask loadSkin(String skinName, final SkinLoaderListener listener) {
        return loadSkin(skinName, listener, SKIN_LOADER_STRATEGY_ASSETS);
    }

    /**
     * 加载皮肤包.
     *
     * @param skinName 皮肤包名称.
     * @param strategy 皮肤包加载策略.
     * @return
     */
    public AsyncTask loadSkin(String skinName, int strategy) {
        return loadSkin(skinName, null, strategy);
    }

    /**
     * 加载皮肤包.
     *
     * @param skinName 皮肤包名称.
     * @param listener 皮肤包加载监听.
     * @param strategy 皮肤包加载策略.
     * @return
     */
    public AsyncTask loadSkin(String skinName, SkinLoaderListener listener, int strategy) {
        return new SkinLoadTask(listener, mStrategyMap.get(strategy)).execute(skinName);
    }

    private class SkinLoadTask extends AsyncTask<String, Void, String> {
        private final SkinLoaderListener mListener;
        private final SkinLoaderStrategy mStrategy;

        SkinLoadTask(@Nullable SkinLoaderListener listener, @NonNull SkinLoaderStrategy strategy) {
            mListener = listener;
            mStrategy = strategy;
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
                        SkinCompatResources.getInstance().reset();
                        return params[0];
                    }
                    if (!TextUtils.isEmpty(
                            mStrategy.loadSkinInBackground(mAppContext, params[0]))) {
                        return params[0];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            SkinCompatResources.getInstance().reset();
            return null;
        }

        protected void onPostExecute(String skinName) {
            synchronized (mLock) {
                // skinName 为""时，恢复默认皮肤
                if (skinName != null) {
                    SkinPreference.getInstance().setSkinName(skinName).setSkinStrategy(mStrategy.getType()).commitEditor();
                    notifyUpdateSkin();
                    if (mListener != null) mListener.onSuccess();
                } else {
                    SkinPreference.getInstance().setSkinName("").setSkinStrategy(SKIN_LOADER_STRATEGY_NONE).commitEditor();
                    if (mListener != null) mListener.onFailed("皮肤资源获取失败");
                }
                mLoading = false;
                mLock.notifyAll();
            }
        }
    }

    /**
     * 获取皮肤包包名.
     *
     * @param skinPkgPath sdcard中皮肤包路径.
     * @return
     */
    public String getSkinPackageName(String skinPkgPath) {
        PackageManager mPm = mAppContext.getPackageManager();
        PackageInfo info = mPm.getPackageArchiveInfo(skinPkgPath, PackageManager.GET_ACTIVITIES);
        return info.packageName;
    }

    /**
     * 获取皮肤包资源{@link Resources}.
     *
     * @param skinPkgPath sdcard中皮肤包路径.
     * @return
     */
    @Nullable
    public Resources getSkinResources(String skinPkgPath) {
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
}