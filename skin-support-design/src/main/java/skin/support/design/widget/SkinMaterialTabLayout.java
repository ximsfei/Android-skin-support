package skin.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;

import skin.support.content.res.SkinCompatResources;
import skin.support.design.R;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;
import skin.support.widget.SkinCompatUtils;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by ximsfei on 17-1-14.
 */

public class SkinMaterialTabLayout extends TabLayout implements SkinCompatSupportable {
    private int mTabIndicatorColorResId = INVALID_ID;
    private int mTabTextColorsResId = INVALID_ID;
    private int mTabSelectedTextColorResId = INVALID_ID;
    private boolean mSkinSupport = true;

    public SkinMaterialTabLayout(Context context) {
        this(context, null);
    }

    public SkinMaterialTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinMaterialTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSkinSupport = SkinCompatUtils.getSkinSupport(context, attrs);
        if (!mSkinSupport) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabLayout,
                defStyleAttr, 0);

        mTabIndicatorColorResId = a.getResourceId(R.styleable.TabLayout_tabIndicatorColor, INVALID_ID);

        int tabTextAppearance = a.getResourceId(R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab);

        // Text colors/sizes come from the text appearance first
        final TypedArray ta = context.obtainStyledAttributes(tabTextAppearance, R.styleable.SkinTextAppearance);
        try {
            mTabTextColorsResId = ta.getResourceId(R.styleable.SkinTextAppearance_android_textColor, INVALID_ID);
        } finally {
            ta.recycle();
        }

        if (a.hasValue(R.styleable.TabLayout_tabTextColor)) {
            // If we have an explicit text color set, use it instead
            mTabTextColorsResId = a.getResourceId(R.styleable.TabLayout_tabTextColor, INVALID_ID);
        }

        if (a.hasValue(R.styleable.TabLayout_tabSelectedTextColor)) {
            // We have an explicit selected text color set, so we need to make merge it with the
            // current colors. This is exposed so that developers can use theme attributes to set
            // this (theme attrs in ColorStateLists are Lollipop+)
            mTabSelectedTextColorResId = a.getResourceId(R.styleable.TabLayout_tabSelectedTextColor, INVALID_ID);
        }
        a.recycle();
        applySkin();
    }

    @Override
    public void applySkin() {
        int tabTextColor = INVALID_ID;
        mTabIndicatorColorResId = SkinCompatHelper.checkResourceId(mTabIndicatorColorResId);
        if (mTabIndicatorColorResId != INVALID_ID) {
            setSelectedTabIndicatorColor(SkinCompatResources.getInstance().getColor(mTabIndicatorColorResId));
        }
        mTabTextColorsResId = SkinCompatHelper.checkResourceId(mTabTextColorsResId);
        if (mTabTextColorsResId != INVALID_ID) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setTabTextColors(SkinCompatResources.getInstance().getColorStateList(mTabTextColorsResId));
            } else {
                tabTextColor = SkinCompatResources.getInstance().getColor(mTabTextColorsResId);
            }
        }
        mTabSelectedTextColorResId = SkinCompatHelper.checkResourceId(mTabSelectedTextColorResId);
        if (mTabSelectedTextColorResId != INVALID_ID) {
            int selected = SkinCompatResources.getInstance().getColor(mTabSelectedTextColorResId);
            if (getTabTextColors() != null) {
                setTabTextColors(getTabTextColors().getDefaultColor(), selected);
            } else {
                setTabTextColors(tabTextColor, selected);
            }
        } else if (tabTextColor != INVALID_ID) {
            setTabTextColors(tabTextColor, tabTextColor);
        }
    }

    @Override
    public boolean getSkinSupport() {
        return mSkinSupport;
    }
}
