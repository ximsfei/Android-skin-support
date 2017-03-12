package skin.support.flycotablayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;

import com.flyco.tablayout.SlidingTabLayout;

import skin.support.content.res.SkinCompatResources;
import skin.support.flycotablayout.R;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by ximsf on 2017/3/8.
 */

public class SkinSlidingTabLayout extends SlidingTabLayout implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mIndicatorColorResId = INVALID_ID;
    private int mUnderlineColorResId = INVALID_ID;
    private int mDividerColorResId = INVALID_ID;
    private int mTextSelectColorResId = INVALID_ID;
    private int mTextUnselectColorResId = INVALID_ID;

    public SkinSlidingTabLayout(Context context) {
        this(context, null, 0);
    }

    public SkinSlidingTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinSlidingTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainAttributes(context, attrs);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SlidingTabLayout);
        mIndicatorColorResId = ta.getResourceId(R.styleable.SlidingTabLayout_tl_indicator_color, INVALID_ID);
        mIndicatorColorResId = SkinCompatHelper.checkResourceId(mIndicatorColorResId);
        mUnderlineColorResId = ta.getResourceId(R.styleable.SlidingTabLayout_tl_underline_color, INVALID_ID);
        mUnderlineColorResId = SkinCompatHelper.checkResourceId(mUnderlineColorResId);
        mDividerColorResId = ta.getResourceId(R.styleable.SlidingTabLayout_tl_divider_color, INVALID_ID);
        mDividerColorResId = SkinCompatHelper.checkResourceId(mDividerColorResId);
        mTextSelectColorResId = ta.getResourceId(R.styleable.SlidingTabLayout_tl_textSelectColor, INVALID_ID);
        mTextSelectColorResId = SkinCompatHelper.checkResourceId(mTextSelectColorResId);
        mTextUnselectColorResId = ta.getResourceId(R.styleable.SlidingTabLayout_tl_textUnselectColor, INVALID_ID);
        mTextUnselectColorResId = SkinCompatHelper.checkResourceId(mTextUnselectColorResId);
        ta.recycle();
        applySlidingTabLayoutResources();
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.onSetBackgroundResource(resId);
        }
    }

    private void applySlidingTabLayoutResources() {
        SkinCompatResources resources = SkinCompatResources.getInstance();
        if (mIndicatorColorResId != INVALID_ID) {
            setIndicatorColor(resources.getColor(getContext(), mIndicatorColorResId));
        }
        if (mUnderlineColorResId != INVALID_ID) {
            setUnderlineColor(resources.getColor(getContext(), mUnderlineColorResId));
        }
        if (mDividerColorResId != INVALID_ID) {
            setDividerColor(resources.getColor(getContext(), mDividerColorResId));
        }
        if (mTextSelectColorResId != INVALID_ID) {
            setTextSelectColor(resources.getColor(getContext(), mTextSelectColorResId));
        }
        if (mTextUnselectColorResId != INVALID_ID) {
            setTextUnselectColor(resources.getColor(getContext(), mTextUnselectColorResId));
        }
    }

    @Override
    public void applySkin() {
        applySlidingTabLayoutResources();
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
    }
}
