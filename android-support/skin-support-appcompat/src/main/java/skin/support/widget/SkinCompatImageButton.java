package skin.support.widget;

import android.content.Context;
import androidx.annotation.DrawableRes;
import androidx.appcompat.R;
import androidx.appcompat.widget.AppCompatImageButton;
import android.util.AttributeSet;

/**
 * Created by ximsfei on 17-1-13.
 */

public class SkinCompatImageButton extends AppCompatImageButton implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private SkinCompatImageHelper mImageHelper;

    public SkinCompatImageButton(Context context) {
        this(context, null);
    }

    public SkinCompatImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.imageButtonStyle);
    }

    public SkinCompatImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
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
        if (mImageHelper != null) {
            mImageHelper.setImageResource(resId);
        }
    }

    @Override
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
        if (mImageHelper != null) {
            mImageHelper.applySkin();
        }
    }

}
