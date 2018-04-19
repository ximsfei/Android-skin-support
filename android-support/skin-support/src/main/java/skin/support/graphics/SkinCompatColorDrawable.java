package skin.support.graphics;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import skin.support.content.res.SkinCompatResources;

public class SkinCompatColorDrawable extends ColorDrawable {

    public SkinCompatColorDrawable() {

    }

    public SkinCompatColorDrawable(int color) {
        super(color);
    }

    public SkinCompatColorDrawable(Context context, int colorResId) {
        super(SkinCompatResources.getColor(context, colorResId));
    }

}
