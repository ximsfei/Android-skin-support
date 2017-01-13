package skin.support.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.utils.SkinLog;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class SkinCompatTextHelper extends SkinCompatHelper {
    private static final String TAG = SkinCompatTextHelper.class.getSimpleName();

    private final TextView mView;

    private int mTextColorResId = INVALID_ID;
    private int mTextColorHintResId = INVALID_ID;

    public SkinCompatTextHelper(TextView view) {
        mView = view;
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        final Context context = mView.getContext();

        // First read the TextAppearance style id
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs,
                R.styleable.SkinCompatTextHelper, defStyleAttr, 0);
        final int ap = a.getResourceId(R.styleable.SkinCompatTextHelper_android_textAppearance, INVALID_ID);
        SkinLog.d(TAG, "ap = " + ap);
        a.recycle();

        if (ap != INVALID_ID) {
            a = TintTypedArray.obtainStyledAttributes(context, ap, R.styleable.SkinTextAppearance);
            if (a.hasValue(R.styleable.SkinTextAppearance_android_textColor)) {
                mTextColorResId = a.getResourceId(R.styleable.SkinTextAppearance_android_textColor, INVALID_ID);
                SkinLog.d(TAG, "mTextColorResId = " + mTextColorResId);
            }
            if (a.hasValue(R.styleable.SkinTextAppearance_android_textColorHint)) {
                mTextColorHintResId = a.getResourceId(
                        R.styleable.SkinTextAppearance_android_textColorHint, INVALID_ID);
                SkinLog.d(TAG, "mTextColorHintResId = " + mTextColorHintResId);
            }
            a.recycle();
        }

        // Now read the style's values
        a = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.SkinTextAppearance,
                defStyleAttr, 0);
        if (a.hasValue(R.styleable.SkinTextAppearance_android_textColor)) {
            mTextColorResId = a.getResourceId(R.styleable.SkinTextAppearance_android_textColor, INVALID_ID);
            SkinLog.d(TAG, "mTextColorResId = " + mTextColorResId);
        }
        if (a.hasValue(R.styleable.SkinTextAppearance_android_textColorHint)) {
            mTextColorHintResId = a.getResourceId(
                    R.styleable.SkinTextAppearance_android_textColorHint, INVALID_ID);
            SkinLog.d(TAG, "mTextColorHintResId = " + mTextColorHintResId);
        }
        a.recycle();
        applySkin();
    }

    public void onSetTextAppearance(Context context, int resId) {
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(context,
                resId, R.styleable.SkinTextAppearance);
        if (a.hasValue(R.styleable.SkinTextAppearance_android_textColor)) {
            mTextColorResId = a.getResourceId(R.styleable.SkinTextAppearance_android_textColor, INVALID_ID);
            SkinLog.d(TAG, "mTextColorResId = " + mTextColorResId);
        }
        if (a.hasValue(R.styleable.SkinTextAppearance_android_textColorHint)) {
            mTextColorHintResId = a.getResourceId(R.styleable.SkinTextAppearance_android_textColorHint, INVALID_ID);
            SkinLog.d(TAG, "mTextColorHintResId = " + mTextColorHintResId);
        }
        a.recycle();
        applySkin();
    }

    public void applySkin() {
        mTextColorResId = checkResourceId(mTextColorResId);
        if (mTextColorResId != INVALID_ID) {
            ColorStateList color = SkinCompatResources.getInstance().getColorStateList(mTextColorResId);
            mView.setTextColor(color);
        }
        mTextColorHintResId = checkResourceId(mTextColorHintResId);
        if (mTextColorHintResId != INVALID_ID) {
            ColorStateList color = SkinCompatResources.getInstance().getColorStateList(mTextColorHintResId);
            mView.setHintTextColor(color);
        }
    }
}
