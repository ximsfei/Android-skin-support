package com.ximsfei.skin_autolayout_app;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatView;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;
import static skin.support.widget.SkinCompatHelper.checkResourceId;

/**
 * Created by pengfengwang on 2017/3/16.
 */

public class SkinCompatDimenView extends SkinCompatView {
    private int mLayoutHeightResId = INVALID_ID;

    public SkinCompatDimenView(Context context) {
        this(context, null);
    }

    public SkinCompatDimenView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinCompatDimenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.SkinHeightHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinHeightHelper_android_layout_height)) {
                mLayoutHeightResId = a.getResourceId(
                        R.styleable.SkinHeightHelper_android_layout_height, INVALID_ID);
            }
        } finally {
            a.recycle();
        }
        applySkin();
    }

    @Override
    public void applySkin() {
        super.applySkin();
        mLayoutHeightResId = checkResourceId(mLayoutHeightResId);
        if (mLayoutHeightResId != INVALID_ID) {
            String typeName = getResources().getResourceTypeName(mLayoutHeightResId);
            if ("dimen".equals(typeName)) {
                ViewGroup.LayoutParams lp = getLayoutParams();
                int dimenHeight = getDimen(mLayoutHeightResId);
                if (lp == null) {
                    lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dimenHeight);
                } else {
                    lp.height = dimenHeight;
                }
                setLayoutParams(lp);
            }
        }
    }

    private int getDimen(int resId) {
        int dimen = (int) getResources().getDimension(resId);
        if (SkinCompatResources.getInstance().isDefaultSkin()) {
            return dimen;
        }

        Resources res = SkinCompatResources.getInstance().getSkinResources();
        String resName = res.getResourceEntryName(resId);

        int targetResId = res.getIdentifier(resName, "dimen",
                SkinCompatResources.getInstance().getSkinPkgName());

        return targetResId == 0 ? dimen : (int) res.getDimension(targetResId);
    }
}
