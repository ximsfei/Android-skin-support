package skin.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;

import skin.support.content.res.SkinCompatResources;
import skin.support.design.R;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;
import skin.support.widget.SkinCompatUtils;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by ximsfei on 17-3-2.
 */

public class SkinMaterialCollapsingToolbarLayout extends CollapsingToolbarLayout implements SkinCompatSupportable {
    private int mContentScrimResId = INVALID_ID;
    private int mStatusBarScrimResId = INVALID_ID;
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private boolean mSkinSupport = true;

    public SkinMaterialCollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    public SkinMaterialCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinMaterialCollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSkinSupport = SkinCompatUtils.getSkinSupport(context, attrs);
        if (!mSkinSupport) {
            return;
        }

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CollapsingToolbarLayout, defStyleAttr,
                R.style.Widget_Design_CollapsingToolbar);
        mContentScrimResId = a.getResourceId(R.styleable.CollapsingToolbarLayout_contentScrim, INVALID_ID);
        mStatusBarScrimResId = a.getResourceId(R.styleable.CollapsingToolbarLayout_statusBarScrim, INVALID_ID);
        a.recycle();
        applyContentScrimResource();
        applyStatusBarScrimResource();
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, 0);
    }

    private void applyStatusBarScrimResource() {
        mStatusBarScrimResId = SkinCompatHelper.checkResourceId(mStatusBarScrimResId);
        if (mStatusBarScrimResId != INVALID_ID) {
            String typeName = getResources().getResourceTypeName(mStatusBarScrimResId);
            if ("color".equals(typeName)) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    int color = SkinCompatResources.getInstance().getColor(mStatusBarScrimResId);
                    setStatusBarScrimColor(color);
                } else {
                    ColorStateList colorStateList = SkinCompatResources.getInstance().getColorStateList(mStatusBarScrimResId);
                    Drawable drawable = getStatusBarScrim();
                    DrawableCompat.setTintList(drawable, colorStateList);
                    setStatusBarScrim(drawable);
                }
            } else if ("drawable".equals(typeName)) {
                Drawable drawable = SkinCompatResources.getInstance().getDrawable(mStatusBarScrimResId);
                setStatusBarScrim(drawable);
            } else if ("mipmap".equals(typeName)) {
                Drawable drawable = SkinCompatResources.getInstance().getMipmap(mStatusBarScrimResId);
                setStatusBarScrim(drawable);
            }
        }
    }

    private void applyContentScrimResource() {
        mContentScrimResId = SkinCompatHelper.checkResourceId(mContentScrimResId);
        if (mContentScrimResId != INVALID_ID) {
            String typeName = getResources().getResourceTypeName(mContentScrimResId);
            if ("color".equals(typeName)) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    int color = SkinCompatResources.getInstance().getColor(mContentScrimResId);
                    setContentScrimColor(color);
                } else {
                    ColorStateList colorStateList = SkinCompatResources.getInstance().getColorStateList(mContentScrimResId);
                    Drawable drawable = getContentScrim();
                    DrawableCompat.setTintList(drawable, colorStateList);
                    setContentScrim(drawable);
                }
            } else if ("drawable".equals(typeName)) {
                Drawable drawable = SkinCompatResources.getInstance().getDrawable(mContentScrimResId);
                setContentScrim(drawable);
            } else if ("mipmap".equals(typeName)) {
                Drawable drawable = SkinCompatResources.getInstance().getMipmap(mContentScrimResId);
                setContentScrim(drawable);
            }
        }
    }

    @Override
    public void applySkin() {
        applyContentScrimResource();
        applyStatusBarScrimResource();
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
    }

    @Override
    public boolean getSkinSupport() {
        return mSkinSupport;
    }
}
