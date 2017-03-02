package skin.support.design.widget;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Field;

import skin.support.content.res.SkinCompatResources;
import skin.support.design.R;
import skin.support.utils.SkinLog;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by ximsfei on 17-3-2.
 */

public class SkinCompatTextInputLayout extends TextInputLayout implements SkinCompatSupportable {
    private static final String TAG = SkinCompatTextInputLayout.class.getSimpleName();
    private int mPasswordToggleResId = INVALID_ID;
    private int mCounterTextColorResId = INVALID_ID;
    private int mErrorTextColorResId = INVALID_ID;
    private int mFocusedTextColorResId = INVALID_ID;
    private int mDefaultTextColorResId = INVALID_ID;
    private TextView mErrorVew;

    public SkinCompatTextInputLayout(Context context) {
        this(context, null);
    }

    public SkinCompatTextInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinCompatTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs,
                R.styleable.TextInputLayout, defStyleAttr, R.style.Widget_Design_TextInputLayout);
        if (a.hasValue(R.styleable.TextInputLayout_android_textColorHint)) {
            mDefaultTextColorResId = mFocusedTextColorResId =
                    a.getResourceId(R.styleable.TextInputLayout_android_textColorHint, INVALID_ID);
        }

        int errorTextAppearance = a.getResourceId(R.styleable.TextInputLayout_errorTextAppearance, INVALID_ID);
        if (errorTextAppearance != INVALID_ID) {
            TintTypedArray errorTA = TintTypedArray.obtainStyledAttributes(context, errorTextAppearance, skin.support.R.styleable.SkinTextAppearance);
            if (errorTA.hasValue(skin.support.R.styleable.SkinTextAppearance_android_textColor)) {
                mErrorTextColorResId = errorTA.getResourceId(skin.support.R.styleable.SkinTextAppearance_android_textColor, INVALID_ID);
                SkinLog.d(TAG, "mErrorTextColorResId = " + SkinCompatHelper.checkResourceId(mErrorTextColorResId));
            }
            errorTA.recycle();
        }
        int counterTextAppearance = a.getResourceId(
                R.styleable.TextInputLayout_counterTextAppearance, INVALID_ID);
        if (counterTextAppearance != INVALID_ID) {
            TintTypedArray counterTA = TintTypedArray.obtainStyledAttributes(context, errorTextAppearance, skin.support.R.styleable.SkinTextAppearance);
            if (counterTA.hasValue(skin.support.R.styleable.SkinTextAppearance_android_textColor)) {
                mCounterTextColorResId = counterTA.getResourceId(skin.support.R.styleable.SkinTextAppearance_android_textColor, INVALID_ID);
                SkinLog.d(TAG, "mCounterTextColorResId = " + SkinCompatHelper.checkResourceId(mCounterTextColorResId));
            }
            counterTA.recycle();
        }
        mPasswordToggleResId = a.getResourceId(R.styleable.TextInputLayout_passwordToggleDrawable, INVALID_ID);
        a.recycle();
        applyErrorTextColorResource();
    }

    private void applyErrorTextColorResource() {
        mErrorTextColorResId = SkinCompatHelper.checkResourceId(mErrorTextColorResId);
        if (mErrorTextColorResId != INVALID_ID) {
            if (mErrorVew == null) {
                getErrorView();
            }
            Log.e("pengfeng", "merror view = " + mErrorVew);
            if (mErrorVew != null) {
                mErrorVew.setTextColor(SkinCompatResources.getInstance().getColor(mErrorTextColorResId));
            }
        }
    }

    private void getErrorView() {
        try {
            Field errorView = TextInputLayout.class.getDeclaredField("mErrorView");
            errorView.setAccessible(true);
            mErrorVew = (TextView) errorView.get(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void applySkin() {
        applyErrorTextColorResource();
    }
}
