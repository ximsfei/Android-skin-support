package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import skin.support.SkinCompatManager;

public class SkinCompatResources {
    private static final Map<Context, SkinCompatResources> sInstanceMap = new HashMap<>();
    private final Context mAppContext;
    private Resources mResources;
    private String mSkinPkgName;
    private String mSkinName;
    private SkinCompatManager.SkinLoaderStrategy mStrategy;
    private boolean isDefaultSkin;

    private SkinCompatResources(Context context) {
        mAppContext = context.getApplicationContext();
        reset();
    }

    public static void init(Context context) {
        SkinCompatResources instance = sInstanceMap.get(context.getApplicationContext());
        if (instance == null) {
            synchronized (SkinCompatResources.class) {
                instance = sInstanceMap.get(context.getApplicationContext());
                if (instance == null) {
                    instance = new SkinCompatResources(context);
                    sInstanceMap.put(context.getApplicationContext(), instance);
                }
            }
        }
    }

    public static SkinCompatResources getInstance(Context context) {
        return sInstanceMap.get(context.getApplicationContext());
    }

    public void reset() {
        mResources = mAppContext.getResources();
        mSkinPkgName = mAppContext.getPackageName();
        mSkinName = "";
        mStrategy = null;
        isDefaultSkin = true;
    }

    @Deprecated
    public void setSkinResource(Resources resources, String pkgName) {
        mResources = resources;
        mSkinPkgName = pkgName;
        mSkinName = "";
        mStrategy = null;
        isDefaultSkin = mAppContext.getPackageName().equals(pkgName);
    }

    public void setupSkin(Resources resources, String pkgName, String skinName, SkinCompatManager.SkinLoaderStrategy strategy) {
        mResources = resources;
        mSkinPkgName = pkgName;
        mSkinName = skinName;
        mStrategy = strategy;
        isDefaultSkin = TextUtils.isEmpty(skinName);
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

        int targetResId = getTargetResId(resId);
        return targetResId == 0 ? originColor : mResources.getColor(targetResId);
    }

    public Drawable getDrawable(int resId) {
        Drawable originDrawable = ContextCompat.getDrawable(mAppContext, resId);
        if (isDefaultSkin) {
            return originDrawable;
        }

        int targetResId = getTargetResId(resId);
        return targetResId == 0 ? originDrawable : mResources.getDrawable(targetResId);
    }

    public ColorStateList getColorStateList(int resId) {
        ColorStateList colorStateList = ContextCompat.getColorStateList(mAppContext, resId);
        if (isDefaultSkin) {
            return colorStateList;
        }

        int targetResId = getTargetResId(resId);
        return targetResId == 0 ? colorStateList : mResources.getColorStateList(targetResId);
    }

    private int getTargetResId(int resId) {
        try {
            String resName = null;
            if (mStrategy != null) {
                resName = mStrategy.getTargetResourceEntryName(mAppContext, mSkinName, resId);
            }
            if (TextUtils.isEmpty(resName)) {
                resName = mAppContext.getResources().getResourceEntryName(resId);
            }
            String type = mAppContext.getResources().getResourceTypeName(resId);
            return mResources.getIdentifier(resName, type, mSkinPkgName);
        } catch (Exception e) {
            // 换肤失败不至于应用崩溃.
            return 0;
        }
    }
}
