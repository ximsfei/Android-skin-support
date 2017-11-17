package skin.support.widget;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;

/**
 * Created by ximsfei on 17-1-14.
 */
public class SkinCompatCompoundButtonHelper extends SkinCompatHelper {
    private final CompoundButton mView;
    private int mButtonResourceId = INVALID_ID;

    public SkinCompatCompoundButtonHelper(CompoundButton view) {
        mView = view;
    }

    void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = mView.getContext().obtainStyledAttributes(attrs, R.styleable.SkinCompatCompoundButton,
                defStyleAttr, INVALID_ID);
        try {
            if (a.hasValue(R.styleable.SkinCompatCompoundButton_android_button)) {
                mButtonResourceId = a.getResourceId(
                        R.styleable.SkinCompatCompoundButton_android_button, INVALID_ID);
            }
        } finally {
            a.recycle();
        }
        applySkin();
    }

    public void setButtonDrawable(int resId) {
        mButtonResourceId = resId;
        applySkin();
    }

    @Override
    public void applySkin() {
        mButtonResourceId = SkinCompatHelper.checkResourceId(mButtonResourceId);
        if (mButtonResourceId != INVALID_ID) {
            mView.setButtonDrawable(SkinCompatResources.getInstance().getDrawable(mButtonResourceId));
        }
    }
}
