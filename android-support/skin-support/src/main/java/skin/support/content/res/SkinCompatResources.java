package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.support.annotation.AnyRes;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.util.TypedValue;

import skin.support.SkinCompatManager;
import skin.support.widget.SkinCompatDrawableManager;

public class SkinCompatResources {
    private static volatile SkinCompatResources sInstance;
    private Resources mResources;
    private String mSkinPkgName = "";
    private String mSkinName = "";
    private SkinCompatManager.SkinLoaderStrategy mStrategy;
    private boolean isDefaultSkin = true;

    private SkinCompatResources() {
    }

    public static SkinCompatResources getInstance() {
        if (sInstance == null) {
            synchronized (SkinCompatResources.class) {
                if (sInstance == null) {
                    sInstance = new SkinCompatResources();
                }
            }
        }
        return sInstance;
    }

    public void reset() {
        mResources = null;
        mSkinPkgName = "";
        mSkinName = "";
        mStrategy = null;
        isDefaultSkin = true;
        SkinCompatDrawableManager.get().clearCaches();
    }

    public void setupSkin(Resources resources, String pkgName, String skinName, SkinCompatManager.SkinLoaderStrategy strategy) {
        mResources = resources;
        mSkinPkgName = pkgName;
        mSkinName = skinName;
        mStrategy = strategy;
        isDefaultSkin = TextUtils.isEmpty(skinName) || TextUtils.isEmpty(pkgName) || resources == null;
        SkinCompatDrawableManager.get().clearCaches();
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

    @Deprecated
    public int getColor(int resId) {
        if (!isDefaultSkin) {
            int targetResId = getTargetResId(SkinCompatManager.getInstance().getContext(), resId);
            if (targetResId != 0) {
                return mResources.getColor(targetResId);
            }
        }
        return SkinCompatManager.getInstance().getContext().getResources().getColor(resId);
    }

    @Deprecated
    public Drawable getDrawable(int resId) {
        if (!isDefaultSkin) {
            int targetResId = getTargetResId(SkinCompatManager.getInstance().getContext(), resId);
            if (targetResId != 0) {
                return mResources.getDrawable(targetResId);
            }
        }
        return SkinCompatManager.getInstance().getContext().getResources().getDrawable(resId);
    }

    @Deprecated
    public ColorStateList getColorStateList(int resId) {
        if (!isDefaultSkin) {
            int targetResId = getTargetResId(SkinCompatManager.getInstance().getContext(), resId);
            if (targetResId != 0) {
                return mResources.getColorStateList(targetResId);
            }
        }
        return SkinCompatManager.getInstance().getContext().getResources().getColorStateList(resId);
    }

    private int getTargetResId(Context context, int resId) {
        try {
            String resName = null;
            if (mStrategy != null) {
                resName = mStrategy.getTargetResourceEntryName(context, mSkinName, resId);
            }
            if (TextUtils.isEmpty(resName)) {
                resName = context.getResources().getResourceEntryName(resId);
            }
            String type = context.getResources().getResourceTypeName(resId);
            return mResources.getIdentifier(resName, type, mSkinPkgName);
        } catch (Exception e) {
            // 换肤失败不至于应用崩溃.
            return 0;
        }
    }

    private int getSkinColor(Context context, int resId) {
        if (!isDefaultSkin) {
            int targetResId = getTargetResId(context, resId);
            if (targetResId != 0) {
                return mResources.getColor(targetResId);
            }
        }
        return context.getResources().getColor(resId);
    }

    private ColorStateList getSkinColorStateList(Context context, int resId) {
        if (!isDefaultSkin) {
            int targetResId = getTargetResId(context, resId);
            if (targetResId != 0) {
                return mResources.getColorStateList(targetResId);
            }
        }
        return context.getResources().getColorStateList(resId);
    }

    private Drawable getSkinDrawable(Context context, int resId) {
        if (!isDefaultSkin) {
            int targetResId = getTargetResId(context, resId);
            if (targetResId != 0) {
                return mResources.getDrawable(targetResId);
            }
        }
        return context.getResources().getDrawable(resId);
    }

    private Drawable getSkinDrawableCompat(Context context, int resId) {
        if (AppCompatDelegate.isCompatVectorFromResourcesEnabled()) {
            if (!isDefaultSkin) {
                try {
                    return SkinCompatDrawableManager.get().getDrawable(context, resId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return AppCompatResources.getDrawable(context, resId);
        } else {
            return getSkinDrawable(context, resId);
        }
    }

    private XmlResourceParser getSkinXml(Context context, int resId) {
        if (!isDefaultSkin) {
            int targetResId = getTargetResId(context, resId);
            if (targetResId != 0) {
                return mResources.getXml(targetResId);
            }
        }
        return context.getResources().getXml(resId);
    }

    private void getSkinValue(Context context, @AnyRes int resId, TypedValue outValue, boolean resolveRefs) {
        if (!isDefaultSkin) {
            int targetResId = getTargetResId(context, resId);
            if (targetResId != 0) {
                mResources.getValue(targetResId, outValue, resolveRefs);
                return;
            }
        }
        context.getResources().getValue(resId, outValue, resolveRefs);
    }

    public static int getColor(Context context, int resId) {
        return getInstance().getSkinColor(context, resId);
    }

    public static ColorStateList getColorStateList(Context context, int resId) {
        return getInstance().getSkinColorStateList(context, resId);
    }

    public static Drawable getDrawable(Context context, int resId) {
        return getInstance().getSkinDrawable(context, resId);
    }

    public static Drawable getDrawableCompat(Context context, int resId) {
        return getInstance().getSkinDrawableCompat(context, resId);
    }

    public static XmlResourceParser getXml(Context context, int resId) {
        return getInstance().getSkinXml(context, resId);
    }

    public static void getValue(Context context, @AnyRes int resId, TypedValue outValue, boolean resolveRefs) {
        getInstance().getSkinValue(context, resId, outValue, resolveRefs);
    }
}
