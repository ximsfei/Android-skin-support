package skin.support.design.widget;

import android.content.Context;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.util.AttributeSet;

import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;

/**
 * Created by ximsf on 2017/3/5.
 */

public class SkinMaterialCoordinatorLayout extends CoordinatorLayout implements SkinCompatSupportable {

    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinMaterialCoordinatorLayout(Context context) {
        this(context, null);
    }

    public SkinMaterialCoordinatorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinMaterialCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, 0);
    }

    @Override
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
    }

}
