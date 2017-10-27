package skin.support.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.CheckBox;

import skin.support.R;

/**
 * Created by ximsfei on 17-1-14.
 */

public class SkinCompatCheckBox extends CheckBox implements SkinCompatSupportable {
    private SkinCompatCompoundButtonHelper mCompoundButtonHelper;

    public SkinCompatCheckBox(Context context) {
        this(context, null);
    }

    public SkinCompatCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, R.attr.checkboxStyle);
    }

    public SkinCompatCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        mCompoundButtonHelper = new SkinCompatCompoundButtonHelper(this);
        mCompoundButtonHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    @Override
    public void setButtonDrawable(@DrawableRes int resId) {
        super.setButtonDrawable(resId);
        if (mCompoundButtonHelper != null) {
            mCompoundButtonHelper.setButtonDrawable(resId);
        }
    }

    @Override
    public void applySkin() {
        if (mCompoundButtonHelper != null) {
            mCompoundButtonHelper.applySkin();
        }
    }

}
