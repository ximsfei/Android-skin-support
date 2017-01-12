package skin.support.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.appcompat.R;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

/**
 * Created by ximsfei on 17-1-12.
 */

public class SkinCompatToolbar extends Toolbar implements SkinCompatHelper {
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
        mBackgroundTintHelper.applySkin();
    }
}
