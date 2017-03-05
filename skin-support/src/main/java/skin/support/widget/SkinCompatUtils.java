package skin.support.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import skin.support.R;

/**
 * Created by ximsf on 2017/3/5.
 */

public class SkinCompatUtils {
    private static final int[] SKIN_SUPPORT_ATTRS = {
            R.attr.skinSupport
    };

    public static boolean getSkinSupport(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, SKIN_SUPPORT_ATTRS);
        boolean support = a.getBoolean(0, true);
        if (a != null) {
            a.recycle();
        }
        return support;
    }
}
