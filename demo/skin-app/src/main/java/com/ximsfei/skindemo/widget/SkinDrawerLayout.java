package com.ximsfei.skindemo.widget;

import android.content.Context;
import androidx.drawerlayout.widget.DrawerLayout;
import android.util.AttributeSet;

import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;

public class SkinDrawerLayout extends DrawerLayout implements SkinCompatSupportable {
    private final SkinCompatBackgroundHelper mBackgroundHelper;

    public SkinDrawerLayout(Context context) {
        this(context, null);
    }

    public SkinDrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mBackgroundHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundHelper.loadFromAttributes(attrs, defStyle);
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    @Override
    public void applySkin() {
        if (mBackgroundHelper != null) {
            mBackgroundHelper.applySkin();
        }
    }
}
