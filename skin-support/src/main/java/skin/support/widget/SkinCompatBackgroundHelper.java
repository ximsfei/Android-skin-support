package skin.support.widget;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.appcompat.R;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;

import skin.support.utils.SkinLog;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class SkinCompatBackgroundHelper {
    private static final String TAG = SkinCompatBackgroundHelper.class.getSimpleName();
    private final View mView;

    private int mBackgroundResId = -1;

    SkinCompatBackgroundHelper(View view) {
        mView = view;
    }

    void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(mView.getContext(), attrs,
                R.styleable.ViewBackgroundHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.ViewBackgroundHelper_android_background)) {
                mBackgroundResId = a.getResourceId(
                        R.styleable.ViewBackgroundHelper_android_background, -1);
                SkinLog.d(TAG, "mBackgroundResId = " + mBackgroundResId);
            }
        } finally {
            a.recycle();
        }
        applySkin();
    }

    void onSetBackgroundResource(int resId) {
        SkinLog.d(TAG, "onSetBackgroundResource res " + resId);
        mBackgroundResId = resId;
        // Update the default background tint
        applySkin();
    }

    public void applySkin() {
        SkinLog.d(TAG, "mBackgroundResId = " + mBackgroundResId);
        if (mBackgroundResId == -1) {
            return;
        }
        String typeName = mView.getResources().getResourceTypeName(mBackgroundResId);
        if ("color".equals(typeName)) {
            ColorStateList colorStateList = SkinCompatResources.getInstance().getColorStateList(mBackgroundResId);
            Drawable drawable = mView.getBackground();
            DrawableCompat.setTintList(drawable, colorStateList);
            mView.setBackgroundDrawable(drawable);
//            int color = SkinResLoader.getInstance().getColor(mBackgroundResId);
//            mView.setBackgroundColor(color);
        } else if ("drawable".equals(typeName)) {
            Drawable drawable = SkinCompatResources.getInstance().getDrawable(mBackgroundResId);
            mView.setBackgroundDrawable(drawable);
        }
    }
}
