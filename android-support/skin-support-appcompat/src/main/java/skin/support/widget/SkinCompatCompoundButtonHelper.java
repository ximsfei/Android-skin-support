package skin.support.widget;

import android.content.res.TypedArray;
import androidx.core.widget.CompoundButtonCompat;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import skin.support.appcompat.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.content.res.SkinCompatVectorResources;

/**
 * Created by ximsfei on 17-1-14.
 */
public class SkinCompatCompoundButtonHelper extends SkinCompatHelper {
    private final CompoundButton mView;
    private int mButtonResourceId = INVALID_ID;
    private int mButtonTintResId = INVALID_ID;

    public SkinCompatCompoundButtonHelper(CompoundButton view) {
        mView = view;
    }

    void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = mView.getContext().obtainStyledAttributes(attrs, R.styleable.CompoundButton,
                defStyleAttr, INVALID_ID);
        try {
            if (a.hasValue(R.styleable.CompoundButton_android_button)) {
                mButtonResourceId = a.getResourceId(
                        R.styleable.CompoundButton_android_button, INVALID_ID);
            }

            if (a.hasValue(R.styleable.CompoundButton_buttonTint)) {
                mButtonTintResId = a.getResourceId(R.styleable.CompoundButton_buttonTint, INVALID_ID);
            }
        } finally {
            a.recycle();
        }
        applySkin();
    }

    public void setButtonDrawable(int resId) {
        mButtonResourceId = resId;
        applySkin();
    }

    @Override
    public void applySkin() {
        mButtonResourceId = SkinCompatHelper.checkResourceId(mButtonResourceId);
        if (mButtonResourceId != INVALID_ID) {
            mView.setButtonDrawable(SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mButtonResourceId));
        }
        mButtonTintResId = SkinCompatHelper.checkResourceId(mButtonTintResId);
        if (mButtonTintResId != INVALID_ID) {
            CompoundButtonCompat.setButtonTintList(mView, SkinCompatResources.getColorStateList(mView.getContext(), mButtonTintResId));
        }
    }
}
