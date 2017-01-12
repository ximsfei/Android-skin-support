package skin.support.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v7.appcompat.R;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import skin.support.utils.SkinLog;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class SkinCompatTextHelper {
    private static final String TAG = SkinCompatTextHelper.class.getSimpleName();

    private final TextView mView;

    int mTextColorResId = -1;
    int mTextColorHintResId = -1;

    SkinCompatTextHelper(TextView view) {
        mView = view;
    }

    void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        final Context context = mView.getContext();

        // First read the TextAppearance style id
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs,
                R.styleable.AppCompatTextHelper, defStyleAttr, 0);
        final int ap = a.getResourceId(R.styleable.AppCompatTextHelper_android_textAppearance, -1);
        SkinLog.d(TAG, "ap = " + ap);
        a.recycle();

        if (ap != -1) {
            a = TintTypedArray.obtainStyledAttributes(context, ap, R.styleable.TextAppearance);
                if (a.hasValue(R.styleable.TextAppearance_android_textColor)) {
                    mTextColorResId = a.getResourceId(R.styleable.TextAppearance_android_textColor, -1);
                    SkinLog.d(TAG, "mTextColorResId = " + mTextColorResId);
                    SkinLog.d(TAG, "mTextColorResId = " + mTextColorResId);
                }
                if (a.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
                    mTextColorHintResId = a.getResourceId(
                            R.styleable.TextAppearance_android_textColorHint, -1);
                    SkinLog.d(TAG, "mTextColorHintResId = " + mTextColorHintResId);
            }
            a.recycle();
        }

        // Now read the style's values
        a = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.TextAppearance,
                defStyleAttr, 0);
            if (a.hasValue(R.styleable.TextAppearance_android_textColor)) {
                mTextColorResId = a.getResourceId(R.styleable.TextAppearance_android_textColor, -1);
                SkinLog.d(TAG, "mTextColorResId = " + mTextColorResId);
            }
            if (a.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
                mTextColorHintResId = a.getResourceId(
                        R.styleable.TextAppearance_android_textColorHint, -1);
                SkinLog.d(TAG, "mTextColorHintResId = " + mTextColorHintResId);
            }
        a.recycle();
        applySkin();
    }

    void onSetTextAppearance(Context context, int resId) {
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(context,
                resId, R.styleable.TextAppearance);
        if (a.hasValue(R.styleable.TextAppearance_android_textColor)) {
            mTextColorResId = a.getResourceId(R.styleable.TextAppearance_android_textColor, -1);
            SkinLog.d(TAG, "mTextColorResId = " + mTextColorResId);
        }
        if (a.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
            mTextColorHintResId = a.getResourceId(R.styleable.TextAppearance_android_textColorHint, -1);
            SkinLog.d(TAG, "mTextColorHintResId = " + mTextColorHintResId);
        }
        a.recycle();
        applySkin();
    }

    public void applySkin() {
        SkinLog.d(TAG, "mTextColorResId = " + mTextColorResId);
        SkinLog.d(TAG, "mTextColorHintResId = " + mTextColorHintResId);
        if (mTextColorResId != -1) {
            ColorStateList color = SkinCompatResources.getInstance().getColorStateList(mTextColorResId);
            mView.setTextColor(color);
            return;
        }
        if (mTextColorHintResId != -1) {
            ColorStateList color = SkinCompatResources.getInstance().getColorStateList(mTextColorHintResId);
            mView.setHintTextColor(color);
            return;
        }
    }
}
