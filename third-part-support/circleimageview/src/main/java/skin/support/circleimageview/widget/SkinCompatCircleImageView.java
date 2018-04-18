package skin.support.circleimageview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import de.hdodenhof.circleimageview.CircleImageView;
import skin.support.circleimageview.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatImageHelper;
import skin.support.widget.SkinCompatSupportable;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by pengfengwang on 2017/3/5.
 */

public class SkinCompatCircleImageView extends CircleImageView implements SkinCompatSupportable {
    private SkinCompatImageHelper mImageHelper;
    private int mFillColorResId = INVALID_ID;
    private int mBorderColorResId = INVALID_ID;

    public SkinCompatCircleImageView(Context context) {
        this(context, null);
    }

    public SkinCompatCircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinCompatCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mImageHelper = new SkinCompatImageHelper(this);
        mImageHelper.loadFromAttributes(attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0);
        mBorderColorResId = a.getResourceId(R.styleable.CircleImageView_civ_border_color, INVALID_ID);
        mFillColorResId = a.getResourceId(R.styleable.CircleImageView_civ_fill_color, INVALID_ID);
        a.recycle();
        applyBorderColorResource();
        applyFillColorResource();
    }

    private void applyFillColorResource() {
        mFillColorResId = SkinCompatHelper.checkResourceId(mFillColorResId);
        if (mFillColorResId != INVALID_ID) {
            int color = SkinCompatResources.getColor(getContext(), mFillColorResId);
            setFillColor(color);
        }
    }

    private void applyBorderColorResource() {
        mBorderColorResId = SkinCompatHelper.checkResourceId(mBorderColorResId);
        if (mBorderColorResId != INVALID_ID) {
            int color = SkinCompatResources.getColor(getContext(), mBorderColorResId);
            setBorderColor(color);
        }
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if (mImageHelper != null) {
            mImageHelper.setImageResource(resId);
        }
    }

    @Override
    public void setBorderColorResource(int borderColorRes) {
        super.setBorderColorResource(borderColorRes);
        mBorderColorResId = borderColorRes;
        applySkin();
    }

    @Override
    public void setFillColorResource(int fillColorRes) {
        super.setFillColorResource(fillColorRes);
        mFillColorResId = fillColorRes;
        applySkin();
    }

    @Override
    public void applySkin() {
        if (mImageHelper != null) {
            mImageHelper.applySkin();
        }
        applyBorderColorResource();
        applyFillColorResource();
    }

}
