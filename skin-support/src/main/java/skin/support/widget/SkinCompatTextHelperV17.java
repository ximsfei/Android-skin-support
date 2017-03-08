package skin.support.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.AttributeSet;
import android.widget.TextView;

import skin.support.R;
import skin.support.content.res.SkinCompatResources;

/**
 * Created by pengfengwang on 2017/3/8.
 */

@RequiresApi(17)
@TargetApi(17)
public class SkinCompatTextHelperV17 extends SkinCompatTextHelper {
    private int mDrawableStartResId = INVALID_ID;
    private int mDrawableEndResId = INVALID_ID;

    public SkinCompatTextHelperV17(TextView view) {
        super(view);
    }

    @Override
    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        final Context context = mView.getContext();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SkinCompatTextHelper,
                defStyleAttr, 0);
        if (a.hasValue(R.styleable.SkinCompatTextHelper_android_drawableStart)) {
            mDrawableStartResId = a.getResourceId(R.styleable.SkinCompatTextHelper_android_drawableStart, INVALID_ID);
            mDrawableStartResId = SkinCompatHelper.checkResourceId(mDrawableStartResId);
        }
        if (a.hasValue(R.styleable.SkinCompatTextHelper_android_drawableEnd)) {
            mDrawableEndResId = a.getResourceId(R.styleable.SkinCompatTextHelper_android_drawableEnd, INVALID_ID);
            mDrawableEndResId = SkinCompatHelper.checkResourceId(mDrawableEndResId);
        }
        a.recycle();
        super.loadFromAttributes(attrs, defStyleAttr);
    }

    @Override
    protected void applyCompoundDrawablesResource() {
        Drawable drawableLeft = null, drawableTop = null, drawableRight = null, drawableBottom = null,
                drawableStart = null, drawableEnd = null;
        mDrawableLeftResId = checkResourceId(mDrawableLeftResId);
        if (mDrawableLeftResId != INVALID_ID) {
            drawableLeft = SkinCompatResources.getInstance().getDrawable(mDrawableLeftResId);
        }
        mDrawableTopResId = checkResourceId(mDrawableTopResId);
        if (mDrawableTopResId != INVALID_ID) {
            drawableTop = SkinCompatResources.getInstance().getDrawable(mDrawableTopResId);
        }
        mDrawableRightResId = checkResourceId(mDrawableRightResId);
        if (mDrawableRightResId != INVALID_ID) {
            drawableRight = SkinCompatResources.getInstance().getDrawable(mDrawableRightResId);
        }
        mDrawableBottomResId = checkResourceId(mDrawableBottomResId);
        if (mDrawableBottomResId != INVALID_ID) {
            drawableBottom = SkinCompatResources.getInstance().getDrawable(mDrawableBottomResId);
        }
        if (mDrawableStartResId != INVALID_ID) {
            drawableStart = SkinCompatResources.getInstance().getDrawable(mDrawableStartResId);
        }
        if (drawableStart == null) {
            drawableStart = drawableLeft;
        }
        if (mDrawableEndResId != INVALID_ID) {
            drawableEnd = SkinCompatResources.getInstance().getDrawable(mDrawableEndResId);
        }
        if (drawableEnd == null) {
            drawableEnd = drawableRight;
        }
        mView.setCompoundDrawablesWithIntrinsicBounds(drawableStart, drawableTop, drawableEnd, drawableBottom);
    }
}
