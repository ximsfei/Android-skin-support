package skin.support.widget;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.appcompat.R;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import skin.support.utils.SkinLog;

/**
 * Created by ximsfei on 2017/1/12.
 */
public class SkinCompatImageHelper {
    private static final String TAG = SkinCompatImageHelper.class.getSimpleName();
    private final ImageView mView;
    private int mSrcResId = -1;

    public SkinCompatImageHelper(ImageView imageView) {
        mView = imageView;
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TintTypedArray a = null;
        try {
            a = TintTypedArray.obtainStyledAttributes(mView.getContext(), attrs,
                    R.styleable.AppCompatImageView, defStyleAttr, 0);

            mSrcResId = a.getResourceId(R.styleable.AppCompatImageView_android_src, -1);
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
        SkinLog.d(TAG, "mSrcResId = " + mSrcResId);
        if (mSrcResId == -1) {
            return;
        }
        String typeName = mView.getResources().getResourceTypeName(mSrcResId);
        if ("color".equals(typeName)) {
            ColorStateList colorStateList = SkinCompatResources.getInstance().getColorStateList(mSrcResId);
            Drawable drawable = mView.getDrawable();
            DrawableCompat.setTintList(drawable, colorStateList);
            mView.setImageDrawable(drawable);
        } else if ("drawable".equals(typeName)) {
            Drawable drawable = SkinCompatResources.getInstance().getDrawable(mSrcResId);
            mView.setImageDrawable(drawable);
        }
    }

}
