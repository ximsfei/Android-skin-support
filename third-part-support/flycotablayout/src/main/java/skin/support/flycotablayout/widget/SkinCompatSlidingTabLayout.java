package skin.support.flycotablayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.flyco.tablayout.SlidingTabLayout;

import skin.support.flycotablayout.R;
import skin.support.widget.SkinCompatSupportable;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by ximsf on 2017/3/8.
 */

public class SkinCompatSlidingTabLayout extends SlidingTabLayout implements SkinCompatSupportable {
    private int mIndicatorColorResId = INVALID_ID;
    private int mUnderlineColorResId = INVALID_ID;
    private int mDividerColorResId = INVALID_ID;
    private int mTextSelectColorResId = INVALID_ID;
    private int mTextUnselectColorResId = INVALID_ID;

    public SkinCompatSlidingTabLayout(Context context) {
        this(context, null, 0);
    }

    public SkinCompatSlidingTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinCompatSlidingTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SlidingTabLayout);
        mIndicatorColorResId = ta.getResourceId(R.styleable.SlidingTabLayout_tl_indicator_color, INVALID_ID);
        mUnderlineColorResId = ta.getColor(R.styleable.SlidingTabLayout_tl_underline_color, INVALID_ID);
        mDividerColorResId = ta.getColor(R.styleable.SlidingTabLayout_tl_divider_color, INVALID_ID);
        mTextSelectColorResId = ta.getColor(R.styleable.SlidingTabLayout_tl_textSelectColor, INVALID_ID);
        mTextUnselectColorResId = ta.getColor(R.styleable.SlidingTabLayout_tl_textUnselectColor, INVALID_ID);
        ta.recycle();
    }

    @Override
    public void applySkin() {

    }
}
