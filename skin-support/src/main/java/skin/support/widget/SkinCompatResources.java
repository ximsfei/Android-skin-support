package skin.support.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import skin.support.utils.SkinLog;

/**
 * Created by ximsfei on 2017/1/11.
 */

public class SkinCompatResources {
    private static final String DEFAULT_STATUS_BAR_COLOR_NAME = "colorPrimaryDark";
    private static volatile SkinCompatResources sInstance;
    private static String mStatusBarColor = DEFAULT_STATUS_BAR_COLOR_NAME;
    private final Context mAppContext;
    private Resources mResources;
    private String mSkinPkgName;
    private boolean isDefaultSkin;

    private SkinCompatResources(Context context) {
        mAppContext = context.getApplicationContext();
        setSkinResource(mAppContext.getResources(), mAppContext.getPackageName());
    }

    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (SkinCompatResources.class) {
                if (sInstance == null) {
                    sInstance = new SkinCompatResources(context);
                }
            }
        }
    }

    public static SkinCompatResources getInstance() {
        return sInstance;
    }

    public void setStatusBarColor(String colorName) {
        if (TextUtils.isEmpty(colorName)) {
            return;
        }
        mStatusBarColor = colorName;
    }

    public void setSkinResource(Resources resources, String pkgName) {
        mResources = resources;
        mSkinPkgName = pkgName;
        isDefaultSkin = mAppContext.getPackageName().equals(pkgName);
    }

    public int getColor(int resId) {
        int originColor = ContextCompat.getColor(mAppContext, resId);
        if (isDefaultSkin) {
            return originColor;
        }

        String resName = mAppContext.getResources().getResourceEntryName(resId);

        int targetResId = mResources.getIdentifier(resName, "color", mSkinPkgName);

        return targetResId == 0 ? originColor : mResources.getColor(targetResId);
    }

    public Drawable getDrawable(int resId) {
        Drawable originDrawable = ContextCompat.getDrawable(mAppContext, resId);
        if (isDefaultSkin) {
            return originDrawable;
        }

        String resName = mAppContext.getResources().getResourceEntryName(resId);

        int targetResId = mResources.getIdentifier(resName, "drawable", mSkinPkgName);

        return targetResId == 0 ? originDrawable : mResources.getDrawable(targetResId);
    }

    public ColorStateList getColorStateList(int resId) {
        ColorStateList originColorList = ContextCompat.getColorStateList(mAppContext, resId);
        if (isDefaultSkin) {
            return originColorList;
        }

        String resName = mAppContext.getResources().getResourceEntryName(resId);

        int targetResId = mResources.getIdentifier(resName, "color", mSkinPkgName);

        return targetResId == 0 ? originColorList : mResources.getColorStateList(targetResId);
    }

    public int getStatusBarColor() {
        int resId = mResources.getIdentifier(mStatusBarColor, "color", mSkinPkgName);
        if (resId == 0) {
            resId = mResources.getIdentifier(DEFAULT_STATUS_BAR_COLOR_NAME, "color", mSkinPkgName);
        }
        SkinLog.d(resId + " resId");
        return resId > 0 ? mResources.getColor(resId) : 0;
    }
}
