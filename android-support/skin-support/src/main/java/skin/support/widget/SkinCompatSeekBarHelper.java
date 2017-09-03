package skin.support.widget;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.SeekBar;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;

/**
 * Created by ximsfei on 17-1-21.
 */
public class SkinCompatSeekBarHelper extends SkinCompatProgressBarHelper {
    private final SeekBar mView;

    private int mThumbResId = INVALID_ID;

    public SkinCompatSeekBarHelper(SeekBar view) {
        super(view);
        mView = view;
    }

    @Override
    void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        super.loadFromAttributes(attrs, defStyleAttr);

        TintTypedArray a = TintTypedArray.obtainStyledAttributes(mView.getContext(), attrs,
                R.styleable.AppCompatSeekBar, defStyleAttr, 0);
        mThumbResId = a.getResourceId(R.styleable.AppCompatSeekBar_android_thumb, INVALID_ID);
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

//        if (a.hasValue(R.styleable.AppCompatSeekBar_tickMarkTint)) {
//            mTickMarkTintList = a.getColorStateList(R.styleable.AppCompatSeekBar_tickMarkTint);
//            mHasTickMarkTint = true;
//        }

        a.recycle();

//        applyTickMarkTint();
        applySkin();
    }

    @Override
    public void applySkin() {
        super.applySkin();
        mThumbResId = checkResourceId(mThumbResId);
        if (mThumbResId != INVALID_ID) {
            mView.setThumb(SkinCompatResources.getInstance().getDrawable(mThumbResId));
        }
    }
}
