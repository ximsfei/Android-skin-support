package skin.support.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by ximsfei on 17-1-12.
 */

public class SkinCompatToolbar extends Toolbar implements SkinCompatSupportable {
    private int mNavigationIconResId = INVALID_ID;
    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinCompatToolbar(Context context) {
        this(context, null);
    }

    public SkinCompatToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.toolbarStyle);
    }

    public SkinCompatToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);

        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                R.styleable.Toolbar, defStyleAttr, 0);
        mNavigationIconResId = a.getResourceId(R.styleable.Toolbar_navigationIcon, INVALID_ID);
        a.recycle();
        applyNavigationIcon();
    }

    private void applyNavigationIcon() {
        mNavigationIconResId = SkinCompatHelper.checkResourceId(mNavigationIconResId);
        if (mNavigationIconResId != INVALID_ID) {
            setNavigationIcon(SkinCompatResources.getInstance().getDrawable(mNavigationIconResId));
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
    public void setNavigationIcon(@DrawableRes int resId) {
        super.setNavigationIcon(resId);
        mNavigationIconResId = resId;
    }

    @Override
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
        applyNavigationIcon();
    }
}
