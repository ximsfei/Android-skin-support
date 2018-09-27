package skin.support.widget;

import android.content.Context;
import androidx.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Jungle68 on 2017/6/27.
 */
public class SkinCompatScrollView extends ScrollView implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinCompatScrollView(Context context) {
        this(context, null);
    }

    public SkinCompatScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinCompatScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
    }

}
