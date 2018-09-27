package skin.support.flycotablayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.DrawableRes;
import android.util.AttributeSet;

import com.flyco.tablayout.widget.MsgView;

import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;
import skin.support.widget.SkinCompatTextHelper;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by pengfengwang on 2017/3/9.
 */

public class SkinMsgView extends MsgView implements SkinCompatSupportable {
    private SkinCompatTextHelper mTextHelper;
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mBackgroundColorResId = INVALID_ID;
    private int mStrokeColorResId = INVALID_ID;
    public SkinMsgView(Context context) {
        this(context, null, 0);
    }

    public SkinMsgView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinMsgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, com.flyco.tablayout.R.styleable.MsgView);
        mBackgroundColorResId = ta.getResourceId(com.flyco.tablayout.R.styleable.MsgView_mv_backgroundColor, INVALID_ID);
        mStrokeColorResId = ta.getResourceId(com.flyco.tablayout.R.styleable.MsgView_mv_strokeColor, INVALID_ID);
        applyBackgroundColorResource();
        applyStrokeColorResource();
        ta.recycle();
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
        mTextHelper = SkinCompatTextHelper.create(this);
        mTextHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.onSetBackgroundResource(resId);
        }
    }

    @Override
    public void setTextAppearance(int resId) {
        setTextAppearance(getContext(), resId);
    }

    @Override
    public void setTextAppearance(Context context, int resId) {
        super.setTextAppearance(context, resId);
        if (mTextHelper != null) {
            mTextHelper.onSetTextAppearance(context, resId);
        }
    }

    public void setBackgroundColorResource(int resId) {
        mBackgroundColorResId = resId;
        applyBackgroundColorResource();
    }

    private void applyBackgroundColorResource() {
        mBackgroundColorResId = SkinCompatHelper.checkResourceId(mBackgroundColorResId);
        if (mBackgroundColorResId != INVALID_ID) {
            setBackgroundColor(SkinCompatResources.getColor(getContext(), mBackgroundColorResId));
        }
    }

    public void setStrokeColorResource(int resId) {
        mStrokeColorResId = resId;
        applyStrokeColorResource();
    }

    private void applyStrokeColorResource() {
        mStrokeColorResId = SkinCompatHelper.checkResourceId(mStrokeColorResId);
        if (mStrokeColorResId != INVALID_ID) {
            setStrokeColor(SkinCompatResources.getColor(getContext(), mStrokeColorResId));
        }
    }

    @Override
    public void applySkin() {
        applyBackgroundColorResource();
        applyStrokeColorResource();
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
        if (mTextHelper != null) {
            mTextHelper.applySkin();
        }
    }
}
