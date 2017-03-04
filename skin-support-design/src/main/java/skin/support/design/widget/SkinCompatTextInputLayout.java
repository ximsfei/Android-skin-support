package skin.support.design.widget;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.design.widget.TextInputLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import skin.support.content.res.SkinCompatResources;
import skin.support.design.R;
import skin.support.utils.SkinLog;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by ximsfei on 17-3-2.
 */

public class SkinCompatTextInputLayout extends TextInputLayout implements SkinCompatSupportable {
    private static final String TAG = SkinCompatTextInputLayout.class.getSimpleName();
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mPasswordToggleResId = INVALID_ID;
    private int mCounterTextColorResId = INVALID_ID;
    private int mErrorTextColorResId = INVALID_ID;
    private int mFocusedTextColorResId = INVALID_ID;
    private int mDefaultTextColorResId = INVALID_ID;

    public SkinCompatTextInputLayout(Context context) {
        this(context, null);
    }

    public SkinCompatTextInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinCompatTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);

        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs,
                R.styleable.TextInputLayout, defStyleAttr, R.style.Widget_Design_TextInputLayout);
        if (a.hasValue(R.styleable.TextInputLayout_android_textColorHint)) {
            mDefaultTextColorResId = mFocusedTextColorResId =
                    a.getResourceId(R.styleable.TextInputLayout_android_textColorHint, INVALID_ID);
        }

        int errorTextAppearance = a.getResourceId(R.styleable.TextInputLayout_errorTextAppearance, INVALID_ID);
        loadErrorTextColorResFromAttributes(errorTextAppearance);
        int counterTextAppearance = a.getResourceId(R.styleable.TextInputLayout_counterTextAppearance, INVALID_ID);
        loadCounterTextColorResFromAttributes(counterTextAppearance);
        mPasswordToggleResId = a.getResourceId(R.styleable.TextInputLayout_passwordToggleDrawable, INVALID_ID);
        a.recycle();
    }

    private void loadCounterTextColorResFromAttributes(@StyleRes int resId) {
        if (resId != INVALID_ID) {
            TintTypedArray counterTA = TintTypedArray.obtainStyledAttributes(getContext(), resId, skin.support.R.styleable.SkinTextAppearance);
            if (counterTA.hasValue(skin.support.R.styleable.SkinTextAppearance_android_textColor)) {
                mCounterTextColorResId = counterTA.getResourceId(skin.support.R.styleable.SkinTextAppearance_android_textColor, INVALID_ID);
                SkinLog.d(TAG, "mCounterTextColorResId name = " + getResources().getResourceName(mCounterTextColorResId));
            }
            counterTA.recycle();
        }
        applyCounterTextColorResource();
    }

    @Override
    public void setCounterEnabled(boolean enabled) {
        super.setCounterEnabled(enabled);
        if (enabled) {
            applyCounterTextColorResource();
        }
    }

    private void applyCounterTextColorResource() {
        mCounterTextColorResId = SkinCompatHelper.checkResourceId(mCounterTextColorResId);
        if (mCounterTextColorResId != INVALID_ID) {
            TextView counterView = getCounterView();
            if (counterView != null) {
//                SkinLog.e(TAG, "mCounterTextColor = " +Integer.toHexString(SkinCompatResources.getInstance().getColor(mCounterTextColorResId)));
                counterView.setTextColor(SkinCompatResources.getInstance().getColor(mCounterTextColorResId));
                updateEditTextBackground();
            }
        }
    }

    private TextView getCounterView() {
        try {
            Field counterView = TextInputLayout.class.getDeclaredField("mCounterView");
            counterView.setAccessible(true);
            return (TextView) counterView.get(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setErrorTextAppearance(@StyleRes int resId) {
        super.setErrorTextAppearance(resId);
        loadErrorTextColorResFromAttributes(resId);
    }

    private void loadErrorTextColorResFromAttributes(@StyleRes int resId) {
        if (resId != INVALID_ID) {
            TintTypedArray errorTA = TintTypedArray.obtainStyledAttributes(getContext(),
                    resId, skin.support.R.styleable.SkinTextAppearance);
            if (errorTA.hasValue(skin.support.R.styleable.SkinTextAppearance_android_textColor)) {
                mErrorTextColorResId = errorTA.getResourceId(skin.support.R.styleable.SkinTextAppearance_android_textColor, INVALID_ID);
                SkinLog.d(TAG, "mErrorTextColorResId = " + SkinCompatHelper.checkResourceId(mErrorTextColorResId));
            }
            errorTA.recycle();
        }
        applyErrorTextColorResource();
    }

    @Override
    public void setErrorEnabled(boolean enabled) {
        super.setErrorEnabled(enabled);
        if (enabled) {
            applyErrorTextColorResource();
        }
    }

    private void applyErrorTextColorResource() {
        mErrorTextColorResId = SkinCompatHelper.checkResourceId(mErrorTextColorResId);
        if (mErrorTextColorResId != INVALID_ID && mErrorTextColorResId != R.color.design_error) {
            TextView errorView = getErrorView();
            if (errorView != null) {
                errorView.setTextColor(SkinCompatResources.getInstance().getColor(mErrorTextColorResId));
                updateEditTextBackground();
            }
        }
    }

    private TextView getErrorView() {
        try {
            Field errorView = TextInputLayout.class.getDeclaredField("mErrorView");
            errorView.setAccessible(true);
            return (TextView) errorView.get(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void updateEditTextBackground() {
        try {
            Method updateEditTextBackground = TextInputLayout.class.getDeclaredMethod("updateEditTextBackground");
            updateEditTextBackground.setAccessible(true);
            updateEditTextBackground.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void applySkin() {
        applyErrorTextColorResource();
        applyCounterTextColorResource();
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
    }
}
