package skin.support.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.utils.SkinLog;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class SkinCompatBackgroundHelper extends SkinCompatHelper {
    private final View mView;

    private int mBackgroundResId = INVALID_ID;

    public SkinCompatBackgroundHelper(View view) {
        mView = view;
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = mView.getContext().obtainStyledAttributes(attrs,
                R.styleable.SkinBackgroundHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinBackgroundHelper_android_background)) {
                mBackgroundResId = a.getResourceId(
                        R.styleable.SkinBackgroundHelper_android_background, INVALID_ID);
            }
        } finally {
            a.recycle();
        }
        applySkin();
    }

    public void onSetBackgroundResource(int resId) {
        mBackgroundResId = resId;
        // Update the default background tint
        applySkin();
    }

    public void applySkin() {
        SkinLog.e("mView = " + mView + ", mBackgroundResId = " + Integer.toHexString(mBackgroundResId));
        if (mBackgroundResId != INVALID_ID) {
            SkinLog.e("mView = " + mView + ", mBackgroundResId res name = "
                    + mView.getResources().getResourceName(mBackgroundResId));
        }
        mBackgroundResId = checkResourceId(mBackgroundResId);
        if (mBackgroundResId == INVALID_ID) {
            return;
        }
        String typeName = mView.getResources().getResourceTypeName(mBackgroundResId);
        if ("color".equals(typeName)) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                int color = SkinCompatResources.getInstance().getColor(mView.getContext(), mBackgroundResId);
                mView.setBackgroundColor(color);
            } else {
                ColorStateList colorStateList = SkinCompatResources.getInstance().getColorStateList(mView.getContext(), mBackgroundResId);
                Drawable drawable = mView.getBackground();
                DrawableCompat.setTintList(drawable, colorStateList);
                ViewCompat.setBackground(mView, drawable);
            }
        } else if ("drawable".equals(typeName)) {
            Drawable drawable = SkinCompatResources.getInstance().getDrawable(mView.getContext(), mBackgroundResId);
            ViewCompat.setBackground(mView, drawable);
        } else if ("mipmap".equals(typeName)) {
            Drawable drawable = SkinCompatResources.getInstance().getMipmap(mView.getContext(), mBackgroundResId);
            ViewCompat.setBackground(mView, drawable);
        }
    }
}
