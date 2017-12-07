package skin.support.flycotablayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;

import com.flyco.tablayout.SegmentTabLayout;

import java.lang.reflect.Field;

import skin.support.content.res.SkinCompatResources;
import skin.support.flycotablayout.R;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by pengfengwang on 2017/3/9.
 */

public class SkinSegmentTabLayout extends SegmentTabLayout implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mIndicatorColorResId = INVALID_ID;
    private int mDividerColorResId = INVALID_ID;
    private int mTextSelectColorResId = INVALID_ID;
    private int mTextUnselectColorResId = INVALID_ID;
    private int mBarColorResId = INVALID_ID;
    private int mBarStrokeColorResId = INVALID_ID;

    public SkinSegmentTabLayout(Context context) {
        this(context, null, 0);
    }

    public SkinSegmentTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinSegmentTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainAttributes(context, attrs);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SegmentTabLayout);
        mIndicatorColorResId = ta.getResourceId(R.styleable.SegmentTabLayout_tl_indicator_color, INVALID_ID);
        mIndicatorColorResId = SkinCompatHelper.checkResourceId(mIndicatorColorResId);
        mDividerColorResId = ta.getResourceId(R.styleable.SegmentTabLayout_tl_divider_color, mIndicatorColorResId);
        mDividerColorResId = SkinCompatHelper.checkResourceId(mDividerColorResId);
        mTextSelectColorResId = ta.getResourceId(R.styleable.SegmentTabLayout_tl_textSelectColor, INVALID_ID);
        mTextSelectColorResId = SkinCompatHelper.checkResourceId(mTextSelectColorResId);
        mTextUnselectColorResId = ta.getResourceId(R.styleable.SegmentTabLayout_tl_textUnselectColor, mIndicatorColorResId);
        mTextUnselectColorResId = SkinCompatHelper.checkResourceId(mTextUnselectColorResId);
        mBarColorResId = ta.getResourceId(R.styleable.SegmentTabLayout_tl_bar_color, INVALID_ID);
        mBarColorResId = SkinCompatHelper.checkResourceId(mBarColorResId);
        mBarStrokeColorResId = ta.getResourceId(R.styleable.SegmentTabLayout_tl_bar_stroke_color, mIndicatorColorResId);
        mBarStrokeColorResId = SkinCompatHelper.checkResourceId(mBarStrokeColorResId);
        ta.recycle();
        applySegmentTabLayoutResources();
    }

    private void applySegmentTabLayoutResources() {
        if (mIndicatorColorResId != INVALID_ID) {
            setIndicatorColor(SkinCompatResources.getColor(getContext(), mIndicatorColorResId));
        }
        if (mDividerColorResId != INVALID_ID) {
            setDividerColor(SkinCompatResources.getColor(getContext(), mDividerColorResId));
        }
        if (mTextSelectColorResId != INVALID_ID) {
            setTextSelectColor(SkinCompatResources.getColor(getContext(), mTextSelectColorResId));
        }
        if (mTextUnselectColorResId != INVALID_ID) {
            setTextUnselectColor(SkinCompatResources.getColor(getContext(), mTextUnselectColorResId));
        }
        if (mBarColorResId != INVALID_ID) {
            setBarColor(SkinCompatResources.getColor(getContext(), mBarColorResId));
        }
        if (mBarStrokeColorResId != INVALID_ID) {
            setBarStrokeColor(SkinCompatResources.getColor(getContext(), mBarStrokeColorResId));
        }
    }

    private void setBarColor(int color) {
        try {
            Field barColor = SegmentTabLayout.class.getDeclaredField("mBarColor");
            barColor.setAccessible(true);
            barColor.set(this, color);
            invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setBarStrokeColor(int color) {
        try {
            Field barStrokeColor = SegmentTabLayout.class.getDeclaredField("mBarStrokeColor");
            barStrokeColor.setAccessible(true);
            barStrokeColor.set(this, color);
            invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.onSetBackgroundResource(resId);
        }
    }

    @Override
    public void applySkin() {
        applySegmentTabLayoutResources();
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
    }
}
