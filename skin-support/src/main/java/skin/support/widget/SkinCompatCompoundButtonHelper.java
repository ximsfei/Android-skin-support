package skin.support.widget;

import android.content.res.TypedArray;
import android.support.v4.widget.CompoundButtonCompat;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;

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
            mButtonResourceId = a.getResourceId(R.styleable.CompoundButton_android_button, INVALID_ID);
            mButtonTintResId = a.getResourceId(R.styleable.CompoundButton_buttonTint, INVALID_ID);
            if (mButtonTintResId == INVALID_ID) {
                mButtonTintResId = SkinCompatThemeUtils.getColorAccentResId(mView.getContext());
            }
        } finally {
            a.recycle();
        }
        applySkin();
    }

    public void setButtonDrawable(int resId) {
        mButtonResourceId = resId;
        applyButtonResource();
    }

    private void applyButtonResource() {
        mButtonResourceId = SkinCompatHelper.checkResourceId(mButtonResourceId);
        if (mButtonResourceId != INVALID_ID) {
            mView.setButtonDrawable(SkinCompatResources.getInstance().getDrawable(mView.getContext(), mButtonResourceId));
        }
    }

    private void applyButtonTintResource() {
        mButtonTintResId = SkinCompatHelper.checkResourceId(mButtonTintResId);
        if (mButtonTintResId != INVALID_ID) {
            CompoundButtonCompat.setButtonTintList(mView, SkinCompatResources.getInstance().getColorStateList(mView.getContext(), mButtonTintResId));
        }
    }

    @Override
    public void applySkin() {
        applyButtonResource();
        applyButtonTintResource();
    }
}
