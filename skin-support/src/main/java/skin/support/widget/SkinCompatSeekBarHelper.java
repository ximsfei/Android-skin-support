package skin.support.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.SeekBar;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.utils.SkinLog;

/**
 * Created by ximsfei on 17-1-21.
 */
public class SkinCompatSeekBarHelper extends SkinCompatProgressBarHelper {
    private final SeekBar mView;

    private int mThumbResId = INVALID_ID;
    private int mTickMarkTintResId = INVALID_ID;

    public SkinCompatSeekBarHelper(SeekBar view) {
        super(view);
        mView = view;
    }

    @Override
    void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = mView.getContext().obtainStyledAttributes(attrs,
                R.styleable.AppCompatSeekBar, defStyleAttr, 0);
        mThumbResId = a.getResourceId(R.styleable.AppCompatSeekBar_android_thumb, INVALID_ID);
        SkinLog.e("mView = " + mView + ", mThumbResId = " + Integer.toHexString(mThumbResId));
        if (mThumbResId != INVALID_ID) {
            SkinLog.e("mView = " + mView + ", mThumbResId res name = " + mView.getResources().getResourceName(mThumbResId));
        }
        mThumbResId = checkThumbResId(mThumbResId);
//        final Drawable drawable = a.getDrawableIfKnown(R.styleable.AppCompatSeekBar_android_thumb);
//        if (drawable != null) {
//            mView.setThumb(drawable);
//        }

//        mTickMarkResId = a.getResourceId(R.styleable.AppCompatSeekBar_tickMark, INVALID_ID);
//        final Drawable tickMark = a.getDrawable(R.styleable.AppCompatSeekBar_tickMark);
//        setTickMark(tickMark);

//        if (a.hasValue(R.styleable.AppCompatSeekBar_tickMarkTintMode)) {
//            mTickMarkTintMode = DrawableUtils.parseTintMode(a.getInt(
//                    R.styleable.AppCompatSeekBar_tickMarkTintMode, -1), mTickMarkTintMode);
//            mHasTickMarkTintMode = true;
//        }

        mTickMarkTintResId = a.getResourceId(R.styleable.AppCompatSeekBar_tickMarkTint, INVALID_ID);
        if (mTickMarkTintResId == INVALID_ID) {
            mTickMarkTintResId = SkinCompatThemeUtils.getColorAccentResId(mView.getContext());
            mTickMarkTintResId = checkResourceId(mTickMarkTintResId);
        }
//        if (a.hasValue(R.styleable.AppCompatSeekBar_tickMarkTint)) {
//            mTickMarkTintList = a.getColorStateList(R.styleable.AppCompatSeekBar_tickMarkTint);
//            mHasTickMarkTint = true;
//        }

        a.recycle();

//        applyTickMarkTint();
        super.loadFromAttributes(attrs, defStyleAttr);
    }

    @Override
    public void applySkin() {
        super.applySkin();
        if (mThumbResId != INVALID_ID) {
            mView.setThumb(SkinCompatResources.getInstance().getDrawable(mView.getContext(), mThumbResId));
        }
        if (mTickMarkTintResId != INVALID_ID) {
            ColorStateList colorStateList = SkinCompatResources.getInstance().getColorStateList(mView.getContext(), mTickMarkTintResId);
            if (Build.VERSION.SDK_INT >= 24) {
                mView.setTickMarkTintList(colorStateList);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                mView.setThumbTintList(colorStateList);
//            } else if (Build.VERSION.SDK_INT >= 16) {
//                DrawableCompat.setTintList(mView.getThumb(), colorStateList);
            }
        }
    }

    private int checkThumbResId(int thumbResId) {
        if (thumbResId == R.drawable.abc_seekbar_thumb_material) {
            return INVALID_ID;
        }
        return checkResourceId(thumbResId);
    }
}
