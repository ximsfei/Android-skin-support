package skin.support.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by ximsfei on 2017/3/25.
 */

public class SkinCompatThemeUtils {
    private static final int[] APPCOMPAT_COLOR_PRIMARY_ATTRS = {
            android.support.v7.appcompat.R.attr.colorPrimary
    };
    private static final int[] APPCOMPAT_COLOR_PRIMARY_DARK_ATTRS = {
            android.support.v7.appcompat.R.attr.colorPrimaryDark
    };
    private static final int[] APPCOMPAT_COLOR_ACCENT_ATTRS = {
            android.support.v7.appcompat.R.attr.colorAccent
    };

    public static int getColorPrimaryResId(Context context) {
        return getResId(context, APPCOMPAT_COLOR_PRIMARY_ATTRS);
    }

    public static int getColorPrimaryDarkResId(Context context) {
        return getResId(context, APPCOMPAT_COLOR_PRIMARY_DARK_ATTRS);
    }

    public static int getColorAccentResId(Context context) {
        return getResId(context, APPCOMPAT_COLOR_ACCENT_ATTRS);
    }

    public static int getTextColorPrimaryResId(Context context) {
        return getResId(context, new int[]{android.R.attr.textColorPrimary});
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static int getStatusBarColorResId(Context context) {
        return getResId(context, new int[]{android.R.attr.statusBarColor});
    }

    public static int getWindowBackgroundResId(Context context) {
        return getResId(context, new int[]{android.R.attr.windowBackground});
    }

    private static int getResId(Context context, int[] attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs);
        final int resId = a.getResourceId(0, INVALID_ID);
        if (a != null) {
            a.recycle();
        }
        return resId;
    }
}
