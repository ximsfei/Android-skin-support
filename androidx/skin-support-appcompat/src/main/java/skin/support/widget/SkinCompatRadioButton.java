package skin.support.widget;

import android.content.Context;
import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatRadioButton;
import android.util.AttributeSet;

import skin.support.appcompat.R;

/**
 * Created by ximsfei on 17-1-14.
 */

public class SkinCompatRadioButton extends AppCompatRadioButton implements SkinCompatSupportable {
    private SkinCompatTextHelper mTextHelper;
    private SkinCompatCompoundButtonHelper mCompoundButtonHelper;
    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinCompatRadioButton(Context context) {
        this(context, null);
    }

    public SkinCompatRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.radioButtonStyle);
    }

    public SkinCompatRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCompoundButtonHelper = new SkinCompatCompoundButtonHelper(this);
        mCompoundButtonHelper.loadFromAttributes(attrs, defStyleAttr);
        mTextHelper = SkinCompatTextHelper.create(this);
        mTextHelper.loadFromAttributes(attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    @Override
    public void setButtonDrawable(@DrawableRes int resId) {
        super.setButtonDrawable(resId);
        if (mCompoundButtonHelper != null) {
            mCompoundButtonHelper.setButtonDrawable(resId);
        }
    }

    @Override
    public void setTextAppearance(int resId) {
        setTextAppearance(getContext(), resId);
    }

    @Override
    public void setTextAppearance(Context context, int resId) {
        super.setTextAppearance(context, resId);
        if (mTextHelper != null) {
            mTextHelper.onSetTextAppearance(context, resId);
        }
    }

    @Override
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(
            @DrawableRes int start, @DrawableRes int top, @DrawableRes int end, @DrawableRes int bottom) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        if (mTextHelper != null) {
            mTextHelper.onSetCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        }
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(
            @DrawableRes int left, @DrawableRes int top, @DrawableRes int right, @DrawableRes int bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        if (mTextHelper != null) {
            mTextHelper.onSetCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        }
    }

    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.onSetBackgroundResource(resId);
        }
    }

    @Override
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
        if (mCompoundButtonHelper != null) {
            mCompoundButtonHelper.applySkin();
        }
        if (mTextHelper != null) {
            mTextHelper.applySkin();
        }
    }

}
