package skin.support.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;

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
        TypedArray a = null;
        try {
            a = mView.getContext().obtainStyledAttributes(attrs, R.styleable.SkinCompatImageView, defStyleAttr, 0);
            mSrcResId = a.getResourceId(R.styleable.SkinCompatImageView_android_src, INVALID_ID);
            int srcCompatResId = a.getResourceId(R.styleable.SkinCompatImageView_srcCompat, INVALID_ID);
            srcCompatResId = checkResourceId(srcCompatResId);
            if (srcCompatResId != INVALID_ID) {
                mSrcResId = srcCompatResId;
            }
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
        if (mSrcResId == INVALID_ID) {
            return;
        }
        Drawable drawable = SkinCompatResources.getInstance().getDrawable(mSrcResId);
        if (drawable != null) {
            mView.setImageDrawable(drawable);
        }
    }
}
