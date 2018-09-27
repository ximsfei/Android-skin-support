package skin.support.widget;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;

import skin.support.appcompat.R;
import skin.support.content.res.SkinCompatVectorResources;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by ximsfei on 17-1-14.
 */

public class SkinCompatCheckedTextView extends AppCompatCheckedTextView implements SkinCompatSupportable {

    private static final int[] TINT_ATTRS = {
            android.R.attr.checkMark
    };
    private int mCheckMarkResId = INVALID_ID;

    private SkinCompatTextHelper mTextHelper;
    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinCompatCheckedTextView(Context context) {
        this(context, null);
    }

    public SkinCompatCheckedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.checkedTextViewStyle);
    }

    public SkinCompatCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
        mTextHelper = SkinCompatTextHelper.create(this);
        mTextHelper.loadFromAttributes(attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, TINT_ATTRS, defStyleAttr, 0);
        mCheckMarkResId = a.getResourceId(0, INVALID_ID);
        a.recycle();
        applyCheckMark();
    }

    @Override
    public void setCheckMarkDrawable(@DrawableRes int resId) {
        mCheckMarkResId = resId;
        applyCheckMark();
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.onSetBackgroundResource(resId);
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
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
        if (mTextHelper != null) {
            mTextHelper.applySkin();
        }
        applyCheckMark();
    }

    private void applyCheckMark() {
        mCheckMarkResId = SkinCompatHelper.checkResourceId(mCheckMarkResId);
        if (mCheckMarkResId != INVALID_ID) {
            setCheckMarkDrawable(SkinCompatVectorResources.getDrawableCompat(getContext(), mCheckMarkResId));
        }
    }
}
