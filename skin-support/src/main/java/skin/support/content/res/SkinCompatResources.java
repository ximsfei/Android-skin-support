package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by ximsfei on 2017/1/11.
 */

public class SkinCompatResources {
    private static volatile SkinCompatResources sInstance;
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

    public void setSkinResource(Resources resources, String pkgName) {
        mResources = resources;
        mSkinPkgName = pkgName;
        isDefaultSkin = mAppContext.getPackageName().equals(pkgName);
    }

    public Resources getSkinResources() {
        return mResources;
    }

    public String getSkinPkgName() {
        return mSkinPkgName;
    }

    public boolean isDefaultSkin() {
        return isDefaultSkin;
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

    public Drawable getMipmap(int resId) {
        Drawable originDrawable = ContextCompat.getDrawable(mAppContext, resId);
        if (isDefaultSkin) {
            return originDrawable;
        }

        String resName = mAppContext.getResources().getResourceEntryName(resId);

        int targetResId = mResources.getIdentifier(resName, "mipmap", mSkinPkgName);

        return targetResId == 0 ? originDrawable : mResources.getDrawable(targetResId);
    }

    public ColorStateList getColorStateList(int resId) {
        ColorStateList colorStateList = ContextCompat.getColorStateList(mAppContext, resId);
        if (isDefaultSkin) {
            return colorStateList;
        }

        String resName = mAppContext.getResources().getResourceEntryName(resId);

        int targetResId = mResources.getIdentifier(resName, "color", mSkinPkgName);

        return targetResId == 0 ? colorStateList : mResources.getColorStateList(targetResId);
    }
}
