package skin.support.design.widget;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;

import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;
import skin.support.widget.SkinCompatUtils;

/**
 * Created by ximsfei on 2017/1/13.
 */

public class SkinMaterialAppBarLayout extends AppBarLayout implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private boolean mSkinSupport = true;

    public SkinMaterialAppBarLayout(Context context) {
        this(context, null);
    }

    public SkinMaterialAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSkinSupport = SkinCompatUtils.getSkinSupport(context, attrs);
        if (!mSkinSupport) {
            return;
        }
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, 0);
    }

    @Override
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
    }

    @Override
    public boolean getSkinSupport() {
        return mSkinSupport;
    }
}
