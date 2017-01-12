package skin.support.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import skin.support.utils.SkinLog;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class SkinCompatImageView extends AppCompatImageView implements SkinCompatHelper {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private SkinCompatImageHelper mImageHelper;

    public SkinCompatImageView(Context context) {
        this(context, null);
    }

    public SkinCompatImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinCompatImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);

        mImageHelper = new SkinCompatImageHelper(this);
        mImageHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.onSetBackgroundResource(resId);
        }
    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        // Intercept this call and instead retrieve the Drawable via the image helper
        mImageHelper.setImageResource(resId);
    }

    @Override
    public void applySkin() {
        mBackgroundTintHelper.applySkin();
        mImageHelper.applySkin();
    }
}
