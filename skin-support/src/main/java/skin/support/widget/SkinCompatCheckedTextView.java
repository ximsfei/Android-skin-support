package skin.support.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;

import skin.support.content.res.SkinCompatResources;

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
        this(context, attrs, android.R.attr.checkedTextViewStyle);
    }

    public SkinCompatCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
        mTextHelper = new SkinCompatTextHelper(this);
        mTextHelper.loadFromAttributes(attrs, defStyleAttr);

        TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                TINT_ATTRS, defStyleAttr, 0);
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
    public void setTextAppearance(Context context, int resId) {
        super.setTextAppearance(context, resId);
        if (mTextHelper != null) {
            mTextHelper.onSetTextAppearance(context, resId);
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
            setCheckMarkDrawable(SkinCompatResources.getInstance().getDrawable(mCheckMarkResId));
        }
    }
}
