package skin.support.design.widget;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;

import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;

/**
 * Created by pengfengwang on 2017/1/15.
 */

public class SkinMaterialNavigationView extends NavigationView implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private boolean mSkinSupport = true;
//    private int mBackgroundResId = INVALID_ID;

    public SkinMaterialNavigationView(Context context) {
        this(context, null);
    }

    public SkinMaterialNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinMaterialNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
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
