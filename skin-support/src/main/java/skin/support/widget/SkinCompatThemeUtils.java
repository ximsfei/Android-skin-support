package skin.support.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by pengfengwang on 2017/3/11.
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
        TypedArray a = context.obtainStyledAttributes(APPCOMPAT_COLOR_PRIMARY_ATTRS);
        final int resId = a.getResourceId(0, INVALID_ID);
        if (a != null) {
            a.recycle();
        }
        return resId;
    }

    public static int getColorPrimaryDarkResId(Context context) {
        TypedArray a = context.obtainStyledAttributes(APPCOMPAT_COLOR_PRIMARY_DARK_ATTRS);
        final int resId = a.getResourceId(0, INVALID_ID);
        if (a != null) {
            a.recycle();
        }
        return resId;
    }

    public static int getColorAccentResId(Context context) {
        TypedArray a = context.obtainStyledAttributes(APPCOMPAT_COLOR_ACCENT_ATTRS);
        final int resId = a.getResourceId(0, INVALID_ID);
        if (a != null) {
            a.recycle();
        }
        return resId;
    }
}
