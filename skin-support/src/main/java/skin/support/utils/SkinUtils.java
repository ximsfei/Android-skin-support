package skin.support.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import skin.support.content.res.SkinCompatResources;

/**
 * @Describe
 * @Author Jungle68
 * @Date 2017/6/28
 * @Contact master.jungle68@gmail.com
 */

public class SkinUtils {

    /**
     * set text color for skin support .
     *
     * @param textView target view
     * @param resId    the color id to set
     */
    @SuppressWarnings("ResourceAsColor")
    public static void setCompatTextColor(TextView textView, int resId) {
        textView.setTextColor(resId);
    }

    public static void setTextColor(TextView textView, int resId) {
        textView.setTextColor(getColor(resId));
    }

    public static int getColor(int resId) {
        return SkinCompatResources.getInstance().getColor(resId);
    }

    public Drawable getDrawable(int resId) {
        return SkinCompatResources.getInstance().getDrawable(resId);
    }

    public Drawable getMipmap(int resId) {
        return SkinCompatResources.getInstance().getDrawable(resId);
    }

    public ColorStateList getColorStateList(int resId) {
        return SkinCompatResources.getInstance().getColorStateList(resId);
    }
}
