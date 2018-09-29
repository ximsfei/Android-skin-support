package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.content.res.AppCompatResources;

public class SkinCompatVectorResources implements SkinResources {
    private static SkinCompatVectorResources sInstance;

    private SkinCompatVectorResources() {
        SkinCompatResources.getInstance().addSkinResources(this);
    }

    public static SkinCompatVectorResources getInstance() {
        if (sInstance == null) {
            synchronized (SkinCompatVectorResources.class) {
                if (sInstance == null) {
                    sInstance = new SkinCompatVectorResources();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void clear() {
        SkinCompatDrawableManager.get().clearCaches();
    }

    private Drawable getSkinDrawableCompat(Context context, int resId) {
        if (AppCompatDelegate.isCompatVectorFromResourcesEnabled()) {
            if (!SkinCompatResources.getInstance().isDefaultSkin()) {
                try {
                    return SkinCompatDrawableManager.get().getDrawable(context, resId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // SkinCompatDrawableManager.get().getDrawable(context, resId) 中会调用getSkinDrawable等方法。
            // 这里只需要拦截使用默认皮肤的情况。
            if (!SkinCompatUserThemeManager.get().isColorEmpty()) {
                ColorStateList colorStateList = SkinCompatUserThemeManager.get().getColorStateList(resId);
                if (colorStateList != null) {
                    return new ColorDrawable(colorStateList.getDefaultColor());
                }
            }
            if (!SkinCompatUserThemeManager.get().isDrawableEmpty()) {
                Drawable drawable = SkinCompatUserThemeManager.get().getDrawable(resId);
                if (drawable != null) {
                    return drawable;
                }
            }
            Drawable drawable = SkinCompatResources.getInstance().getStrategyDrawable(context, resId);
            if (drawable != null) {
                return drawable;
            }
            return AppCompatResources.getDrawable(context, resId);
        } else {
            if (!SkinCompatUserThemeManager.get().isColorEmpty()) {
                ColorStateList colorStateList = SkinCompatUserThemeManager.get().getColorStateList(resId);
                if (colorStateList != null) {
                    return new ColorDrawable(colorStateList.getDefaultColor());
                }
            }
            if (!SkinCompatUserThemeManager.get().isDrawableEmpty()) {
                Drawable drawable = SkinCompatUserThemeManager.get().getDrawable(resId);
                if (drawable != null) {
                    return drawable;
                }
            }
            Drawable drawable = SkinCompatResources.getInstance().getStrategyDrawable(context, resId);
            if (drawable != null) {
                return drawable;
            }
            if (!SkinCompatResources.getInstance().isDefaultSkin()) {
                int targetResId = SkinCompatResources.getInstance().getTargetResId(context, resId);
                if (targetResId != 0) {
                    return SkinCompatResources.getInstance().getSkinResources().getDrawable(targetResId);
                }
            }
            return AppCompatResources.getDrawable(context, resId);
        }
    }

    public static Drawable getDrawableCompat(Context context, int resId) {
        return getInstance().getSkinDrawableCompat(context, resId);
    }
}
