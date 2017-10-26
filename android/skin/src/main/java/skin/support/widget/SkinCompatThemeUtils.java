package skin.support.widget;

import android.content.Context;
import android.content.res.TypedArray;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

public class SkinCompatThemeUtils {

    public static int getWindowBackgroundResId(Context context) {
        return getResId(context, new int[]{android.R.attr.windowBackground});
    }

    private static int getResId(Context context, int[] attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs);
        final int resId = a.getResourceId(0, INVALID_ID);
        a.recycle();
        return resId;
    }
}
