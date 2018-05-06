package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

interface SkinResources {
    void clear();
    ColorStateList getColor(Context context, int resId);
    ColorStateList getColorStateList(Context context, int resId);
    Drawable getDrawable(Context context, int resId);
}
