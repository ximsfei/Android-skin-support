package skin.support.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
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

    public static SkinCompatTextHelper create(TextView textView) {
        if (Build.VERSION.SDK_INT >= 17) {
            return new SkinCompatTextHelperV17(textView);
        }
        return new SkinCompatTextHelper(textView);
    }

    final TextView mView;

    private int mTextColorResId = INVALID_ID;
    private int mTextColorHintResId = INVALID_ID;
    protected int mDrawableBottomResId = INVALID_ID;
    protected int mDrawableLeftResId = INVALID_ID;
    protected int mDrawableRightResId = INVALID_ID;
    protected int mDrawableTopResId = INVALID_ID;

    public SkinCompatTextHelper(TextView view) {
        mView = view;
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        final Context context = mView.getContext();

        // First read the TextAppearance style id
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.SkinCompatTextHelper, defStyleAttr, 0);
        final int ap = a.getResourceId(R.styleable.SkinCompatTextHelper_android_textAppearance, INVALID_ID);
        SkinLog.d(TAG, "ap = " + ap);

        if (a.hasValue(R.styleable.AppCompatTextHelper_android_drawableLeft)) {
            mDrawableLeftResId = a.getResourceId(R.styleable.AppCompatTextHelper_android_drawableLeft, INVALID_ID);
        }
        if (a.hasValue(R.styleable.AppCompatTextHelper_android_drawableTop)) {
            mDrawableTopResId = a.getResourceId(R.styleable.AppCompatTextHelper_android_drawableTop, INVALID_ID);
        }
        if (a.hasValue(R.styleable.AppCompatTextHelper_android_drawableRight)) {
            mDrawableRightResId = a.getResourceId(R.styleable.AppCompatTextHelper_android_drawableRight, INVALID_ID);
        }
        if (a.hasValue(R.styleable.AppCompatTextHelper_android_drawableBottom)) {
            mDrawableBottomResId = a.getResourceId(R.styleable.AppCompatTextHelper_android_drawableBottom, INVALID_ID);
        }
        a.recycle();

        if (ap != INVALID_ID) {
            a = context.obtainStyledAttributes(ap, R.styleable.SkinTextAppearance);
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
        a = context.obtainStyledAttributes(attrs, R.styleable.SkinTextAppearance,
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
        final TypedArray a = context.obtainStyledAttributes(resId, R.styleable.SkinTextAppearance);
        if (a.hasValue(R.styleable.SkinTextAppearance_android_textColor)) {
            mTextColorResId = a.getResourceId(R.styleable.SkinTextAppearance_android_textColor, INVALID_ID);
            SkinLog.d(TAG, "mTextColorResId = " + mTextColorResId);
        }
        if (a.hasValue(R.styleable.SkinTextAppearance_android_textColorHint)) {
            mTextColorHintResId = a.getResourceId(R.styleable.SkinTextAppearance_android_textColorHint, INVALID_ID);
            SkinLog.d(TAG, "mTextColorHintResId = " + mTextColorHintResId);
        }
        a.recycle();
        applyTextColorResource();
        applyTextColorHintResource();
    }

    private void applyTextColorHintResource() {
        SkinLog.e("mView = " + mView + ", mTextColorHintResId = " + Integer.toHexString(mTextColorHintResId));
        if (mTextColorHintResId != INVALID_ID) {
            SkinLog.e("mView = " + mView + ", mTextColorHintResId name = " + mView.getResources().getResourceName(mTextColorHintResId));
        }
        mTextColorHintResId = checkResourceId(mTextColorHintResId);
        if (mTextColorHintResId == R.color.abc_hint_foreground_material_light) {
            return;
        }
        if (mTextColorHintResId != INVALID_ID) {
            ColorStateList color = SkinCompatResources.getInstance().getColorStateList(mView.getContext(), mTextColorHintResId);
            mView.setHintTextColor(color);
        }
    }

    private void applyTextColorResource() {
        SkinLog.e("mView = " + mView + ", mTextColorResId = " + Integer.toHexString(mTextColorResId));
        if (mTextColorResId != INVALID_ID) {
            SkinLog.e("mView = " + mView + ", mTextColorResId name = " + mView.getResources().getResourceName(mTextColorResId));
        }
        mTextColorResId = checkResourceId(mTextColorResId);
        if (mTextColorResId == R.color.abc_primary_text_disable_only_material_light
                || mTextColorResId == R.color.abc_secondary_text_material_light) {
            return;
        }
        if (mTextColorResId != INVALID_ID) {
            ColorStateList color = SkinCompatResources.getInstance().getColorStateList(mView.getContext(), mTextColorResId);
            mView.setTextColor(color);
        }
    }

    protected void applyCompoundDrawablesResource() {
        Drawable drawableLeft = null, drawableTop = null, drawableRight = null, drawableBottom = null;
        mDrawableLeftResId = checkResourceId(mDrawableLeftResId);
        if (mDrawableLeftResId != INVALID_ID) {
            drawableLeft = SkinCompatResources.getInstance().getDrawable(mView.getContext(), mDrawableLeftResId);
        }
        mDrawableTopResId = checkResourceId(mDrawableTopResId);
        if (mDrawableTopResId != INVALID_ID) {
            drawableTop = SkinCompatResources.getInstance().getDrawable(mView.getContext(), mDrawableTopResId);
        }
        mDrawableRightResId = checkResourceId(mDrawableRightResId);
        if (mDrawableRightResId != INVALID_ID) {
            drawableRight = SkinCompatResources.getInstance().getDrawable(mView.getContext(), mDrawableRightResId);
        }
        mDrawableBottomResId = checkResourceId(mDrawableBottomResId);
        if (mDrawableBottomResId != INVALID_ID) {
            drawableBottom = SkinCompatResources.getInstance().getDrawable(mView.getContext(), mDrawableBottomResId);
        }
        mView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

    public int getTextColorResId() {
        return mTextColorResId;
    }

    public void applySkin() {
        applyTextColorResource();
        applyTextColorHintResource();
        applyCompoundDrawablesResource();
    }
}
