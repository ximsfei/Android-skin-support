package skin.support.content.res;

import android.content.Context;

import static skin.support.content.res.SkinCompatThemeUtils.getResId;

/**
 * Created by ximsfei on 2017/3/25.
 */

public class SkinCompatV7ThemeUtils {

    private static final int[] APPCOMPAT_COLOR_PRIMARY_ATTRS = {
            androidx.appcompat.R.attr.colorPrimary
    };
    private static final int[] APPCOMPAT_COLOR_PRIMARY_DARK_ATTRS = {
            androidx.appcompat.R.attr.colorPrimaryDark
    };
    private static final int[] APPCOMPAT_COLOR_ACCENT_ATTRS = {
            androidx.appcompat.R.attr.colorAccent
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
}
