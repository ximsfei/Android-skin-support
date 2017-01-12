package skin.support;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

import skin.support.observe.SkinObservable;
import skin.support.utils.SkinConstants;
import skin.support.utils.SkinFileUtils;
import skin.support.utils.SkinLog;
import skin.support.utils.SkinPreference;
import skin.support.widget.SkinCompatResources;

/**
 * Created by ximsfei on 17-1-10.
 */

public class SkinCompatManager extends SkinObservable {
    private static volatile SkinCompatManager sInstance;
    private final Context mAppContext;

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

    private SkinCompatManager(Context context) {
        mAppContext = context.getApplicationContext();
        SkinPreference.init(mAppContext);
        SkinCompatResources.init(mAppContext);
    }

    public void restoreDefaultTheme() {
        SkinPreference.getInstance().setSkinPath("").commitEditor();
        SkinCompatResources.getInstance().setSkinResource(mAppContext.getResources(), mAppContext.getPackageName());
        notifyUpdateSkin();
    }

    public SkinCompatManager setStatusBarColor(String colorName) {
        SkinCompatResources.getInstance().setStatusBarColor(colorName);
        return this;
    }

    public void loadSkin() {
        String skin = SkinPreference.getInstance().getSkinPath();
        if (TextUtils.isEmpty(skin)) {
            return;
        }
        loadSkin(skin, null);
    }

    public void loadSkin(SkinLoaderListener listener) {
        String skin = SkinPreference.getInstance().getSkinPath();
        if (TextUtils.isEmpty(skin)) {
            return;
        }
        loadSkin(skin, listener);
    }

    public void loadSkin(String skinName, final SkinLoaderListener listener) {
        new SkinLoadTask(listener).execute(skinName);
    }

    private class SkinLoadTask extends AsyncTask<String, Void, Boolean> {

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
        protected Boolean doInBackground(String... params) {
            try {
                if (params.length == 1) {
                    SkinLog.d("skinPkgPath", params[0]);
                    String skinPkgPath = SkinFileUtils.getSkinDir(mAppContext) + File.separator + params[0];
                    // ToDo 方便调试, 每次点击都从assets中读取
//                    if (!isSkinExists(params[0])) {
                        copySkinFromAssets(params[0]);
                        if (!isSkinExists(params[0])) {
                            return false;
                        }
//                    }

                    PackageManager mPm = mAppContext.getPackageManager();
                    PackageInfo mInfo = mPm.getPackageArchiveInfo(skinPkgPath, PackageManager.GET_ACTIVITIES);
                    String pkgName = mInfo.packageName;
                    AssetManager assetManager = AssetManager.class.newInstance();
                    Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
                    addAssetPath.invoke(assetManager, skinPkgPath);

                    Resources superRes = mAppContext.getResources();
                    Resources resources = null;
                    try {
                        resources = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SkinPreference.getInstance().setSkinPath(params[0]).commitEditor();

                    if (resources != null) {
                        SkinCompatResources.getInstance().setSkinResource(resources, pkgName);
                        return true;
                    }
                    SkinCompatResources.getInstance().setSkinResource(
                            mAppContext.getResources(), mAppContext.getPackageName());
                    return false;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(Boolean result) {
            SkinLog.e("result = " + result);
            if (result != null && result) {
                if (mListener != null) mListener.onSuccess();
                notifyUpdateSkin();
            } else {
                if (mListener != null) mListener.onFailed("皮肤资源获取失败");
            }
        }
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
