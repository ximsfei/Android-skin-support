package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
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

    public int getColor(Context context, int resId) {
        int originColor = ContextCompat.getColor(context, resId);
        if (isDefaultSkin) {
            return originColor;
        }

        String resName = context.getResources().getResourceEntryName(resId);

        int targetResId = mResources.getIdentifier(resName, "color", mSkinPkgName);

        return targetResId == 0 ? originColor : mResources.getColor(targetResId);
    }

    public Drawable getDrawable(Context context, int resId) {
        Drawable originDrawable = ContextCompat.getDrawable(context, resId);
        if (isDefaultSkin) {
            return originDrawable;
        }

        String resName = context.getResources().getResourceEntryName(resId);

        int targetResId = mResources.getIdentifier(resName, "drawable", mSkinPkgName);

        return targetResId == 0 ? originDrawable : getDrawable(targetResId, context.getTheme());
    }

    private Drawable getDrawable(int resId, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= 21) {
            return mResources.getDrawable(resId, theme);
        } else {
            return mResources.getDrawable(resId);
        }
    }

    public Drawable getMipmap(Context context, int resId) {
        Drawable originDrawable = ContextCompat.getDrawable(context, resId);
        if (isDefaultSkin) {
            return originDrawable;
        }

        String resName = context.getResources().getResourceEntryName(resId);

        int targetResId = mResources.getIdentifier(resName, "mipmap", mSkinPkgName);

        return targetResId == 0 ? originDrawable : getDrawable(targetResId, context.getTheme());
    }

    public ColorStateList getColorStateList(Context context, int resId) {
        ColorStateList colorStateList = ContextCompat.getColorStateList(context, resId);
        if (isDefaultSkin) {
            return colorStateList;
        }

        String resName = context.getResources().getResourceEntryName(resId);

        int targetResId = mResources.getIdentifier(resName, "color", mSkinPkgName);

        return targetResId == 0 ? colorStateList : mResources.getColorStateList(targetResId);
    }
}
