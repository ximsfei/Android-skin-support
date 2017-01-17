package skin.support.widget;

import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.utils.SkinLog;

/**
 * Created by ximsfei on 2017/1/12.
 */
public class SkinCompatImageHelper extends SkinCompatHelper {
    private static final String TAG = SkinCompatImageHelper.class.getSimpleName();
    private final ImageView mView;
    private int mSrcResId = INVALID_ID;

    public SkinCompatImageHelper(ImageView imageView) {
        mView = imageView;
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TintTypedArray a = null;
        try {
            a = TintTypedArray.obtainStyledAttributes(mView.getContext(), attrs,
                    R.styleable.SkinCompatImageView, defStyleAttr, 0);

            mSrcResId = a.getResourceId(R.styleable.SkinCompatImageView_android_src, -1);
        } finally {
            if (a != null) {
                a.recycle();
            }
        }
        applySkin();
    }

    public void setImageResource(int resId) {
        mSrcResId = resId;
        applySkin();
    }

    public void applySkin() {
        mSrcResId = checkResourceId(mSrcResId);
        SkinLog.d(TAG, "mSrcResId = " + mSrcResId);
        if (mSrcResId == INVALID_ID) {
            return;
        }
        String typeName = mView.getResources().getResourceTypeName(mSrcResId);
        if ("color".equals(typeName)) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                int color = SkinCompatResources.getInstance().getColor(mSrcResId);
                Drawable drawable = mView.getDrawable();
                if (drawable instanceof ColorDrawable) {
                    ((ColorDrawable) drawable.mutate()).setColor(color);
                } else {
                    mView.setImageDrawable(new ColorDrawable(color));
                }
            } else {
                ColorStateList colorStateList = SkinCompatResources.getInstance().getColorStateList(mSrcResId);
                Drawable drawable = mView.getDrawable();
                DrawableCompat.setTintList(drawable, colorStateList);
                mView.setImageDrawable(drawable);
            }
        } else if ("drawable".equals(typeName)) {
            Drawable drawable = SkinCompatResources.getInstance().getDrawable(mSrcResId);
            mView.setImageDrawable(drawable);
        }
    }

}
