package com.ximsfei.dynamicskindemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;

import com.ximsfei.dynamicskindemo.R;

import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatImageHelper;
import skin.support.widget.SkinCompatSupportable;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by ximsfei on 2017/1/17.
 */

public class CustomCircleImageView extends CircleImageView implements SkinCompatSupportable {
    private final SkinCompatImageHelper mImageHelper;
    private int mFillColorResId = INVALID_ID;
    private int mBorderColorResId = INVALID_ID;

    public CustomCircleImageView(Context context) {
        this(context, null);
    }

    public CustomCircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mImageHelper = new SkinCompatImageHelper(this);
        mImageHelper.loadFromAttributes(attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0);
        mBorderColorResId = a.getResourceId(R.styleable.CircleImageView_civ_border_color, INVALID_ID);
        mFillColorResId = a.getResourceId(R.styleable.CircleImageView_civ_fill_color, INVALID_ID);

        a.recycle();
        applySkin();
    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
        if (mImageHelper != null) {
            mImageHelper.applySkin();
        }
    }

    @Override
    public void setBorderColorResource(@ColorRes int borderColorRes) {
        super.setBorderColorResource(borderColorRes);
        mBorderColorResId = borderColorRes;
        applySkin();
    }

    @Override
    public void setFillColorResource(@ColorRes int fillColorRes) {
        super.setFillColorResource(fillColorRes);
        mFillColorResId = fillColorRes;
        applySkin();
    }

    @Override
    public void applySkin() {
        if (mImageHelper != null) {
            mImageHelper.applySkin();
        }

        mBorderColorResId = SkinCompatHelper.checkResourceId(mBorderColorResId);
        if (mBorderColorResId != INVALID_ID) {
            int color = SkinCompatResources.getInstance().getColor(mBorderColorResId);
            setBorderColor(color);
        }

        mFillColorResId = SkinCompatHelper.checkResourceId(mFillColorResId);
        if (mFillColorResId != INVALID_ID) {
            int color = SkinCompatResources.getInstance().getColor(mFillColorResId);
            setFillColor(color);
        }
    }
}
