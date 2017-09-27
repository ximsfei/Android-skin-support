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
        String typeName = mView.getResources().getResourceTypeName(mSrcResId);
        if ("color".equals(typeName)) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                int color = SkinCompatResources.getInstance(mView.getContext()).getColor(mSrcResId);
                Drawable drawable = mView.getDrawable();
                if (drawable != null && drawable instanceof ColorDrawable) {
                    ((ColorDrawable) drawable.mutate()).setColor(color);
                } else {
                    mView.setImageDrawable(new ColorDrawable(color));
                }
            } else {
                ColorStateList colorStateList = SkinCompatResources.getInstance(mView.getContext()).getColorStateList(mSrcResId);
                Drawable drawable = mView.getDrawable();
                if (drawable != null) {
                    DrawableCompat.setTintList(drawable, colorStateList);
                    mView.setImageDrawable(drawable);
                } else {
                    ColorDrawable colorDrawable = new ColorDrawable();
                    colorDrawable.setTintList(colorStateList);
                    mView.setImageDrawable(colorDrawable);
                }
            }
        } else if ("drawable".equals(typeName)) {
            Drawable drawable = SkinCompatResources.getInstance(mView.getContext()).getDrawable(mSrcResId);
            mView.setImageDrawable(drawable);
        } else if ("mipmap".equals(typeName)) {
            Drawable drawable = SkinCompatResources.getInstance(mView.getContext()).getMipmap(mSrcResId);
            mView.setImageDrawable(drawable);
        }
    }

}
