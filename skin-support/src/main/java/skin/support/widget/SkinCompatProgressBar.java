package skin.support.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by ximsfei on 2017/1/19.
 */

public class SkinCompatProgressBar extends ProgressBar implements SkinCompatSupportable {
    private int mIndeterminateDrawableResId = INVALID_ID;

    public SkinCompatProgressBar(Context context) {
        this(context, null);
    }

    public SkinCompatProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.progressBarStyle);
    }

    public SkinCompatProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SkinCompatProgressBar, defStyleAttr, INVALID_ID);
        mIndeterminateDrawableResId = a.getResourceId(R.styleable.SkinCompatProgressBar_android_indeterminateDrawable, INVALID_ID);
        a.recycle();
        applySkin();
    }

    @Override
    public void applySkin() {
        mIndeterminateDrawableResId = SkinCompatHelper.checkResourceId(mIndeterminateDrawableResId);
        if (mIndeterminateDrawableResId != INVALID_ID) {
            Drawable drawable = SkinCompatResources.getInstance().getDrawable(mIndeterminateDrawableResId);
            setIndeterminateDrawable(drawable);
        }
    }
}
