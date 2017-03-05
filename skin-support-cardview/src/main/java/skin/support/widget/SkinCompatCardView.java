package skin.support.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import skin.support.cardview.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.utils.SkinLog;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by ximsfei on 2017/3/5.
 */

public class SkinCompatCardView extends CardView implements SkinCompatSupportable {
    private static final int[] COLOR_BACKGROUND_ATTR = {android.R.attr.colorBackground};
    private int mThemeColorBackgroundResId = INVALID_ID;
    private int mBackgroundColorResId = INVALID_ID;
    private boolean mSkinSupport = true;

    public SkinCompatCardView(Context context) {
        this(context, null);
    }

    public SkinCompatCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinCompatCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSkinSupport = SkinCompatUtils.getSkinSupport(context, attrs);
        if (!mSkinSupport) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CardView, defStyleAttr,
                R.style.CardView);
        if (a.hasValue(R.styleable.CardView_cardBackgroundColor)) {
            mBackgroundColorResId = a.getResourceId(R.styleable.CardView_cardBackgroundColor, INVALID_ID);
        } else {
            final TypedArray aa = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            mThemeColorBackgroundResId = aa.getResourceId(0, INVALID_ID);
            aa.recycle();
        }
        applyBackgroundColorResource();
    }

    private void applyBackgroundColorResource() {
        mBackgroundColorResId = SkinCompatHelper.checkResourceId(mBackgroundColorResId);
        mThemeColorBackgroundResId = SkinCompatHelper.checkResourceId(mThemeColorBackgroundResId);
        ColorStateList backgroundColor;
        if (mBackgroundColorResId != INVALID_ID) {
            SkinLog.d("CardView", "mBackgroundColorResId = " + mBackgroundColorResId
                    + ", res name = " + getResources().getResourceName(mBackgroundColorResId));
            backgroundColor = SkinCompatResources.getInstance().getColorStateList(mBackgroundColorResId);
            setCardBackgroundColor(backgroundColor);
        } else if (mThemeColorBackgroundResId != INVALID_ID) {
            SkinLog.d("CardView", "mThemeColorBackgroundResId = " + mThemeColorBackgroundResId
                    + ", res name = " + getResources().getResourceName(mThemeColorBackgroundResId));
            int themeColorBackground = SkinCompatResources.getInstance().getColor(mThemeColorBackgroundResId);
            final float[] hsv = new float[3];
            Color.colorToHSV(themeColorBackground, hsv);
            backgroundColor = ColorStateList.valueOf(hsv[2] > 0.5f
                    ? getResources().getColor(R.color.cardview_light_background)
                    : getResources().getColor(R.color.cardview_dark_background));
            setCardBackgroundColor(backgroundColor);
        }
    }

    @Override
    public void applySkin() {
        applyBackgroundColorResource();
    }

    @Override
    public boolean getSkinSupport() {
        return mSkinSupport;
    }
}
