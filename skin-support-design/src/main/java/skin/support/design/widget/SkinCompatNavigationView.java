package skin.support.design.widget;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;

import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;

/**
 * Created by pengfengwang on 2017/1/15.
 */

public class SkinCompatNavigationView extends NavigationView implements SkinCompatSupportable {
    private final SkinCompatBackgroundHelper mBackgroundTintHelper;
//    private int mBackgroundResId = INVALID_ID;

    public SkinCompatNavigationView(Context context) {
        this(context, null);
    }

    public SkinCompatNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinCompatNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, 0);
        applySkin();
    }

    @Override
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
    }
}
