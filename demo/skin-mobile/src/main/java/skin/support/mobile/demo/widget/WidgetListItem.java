package skin.support.mobile.demo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import skin.support.mobile.demo.R;

public class WidgetListItem extends LinearLayout {
    private ImageView mLogoIv;
    private TextView mTitleTv;
    private TextView mSubtitleTv;
    private TextView mActionTv;
    private ImageView mArrowIv;
    private View mDividerV;

    public WidgetListItem(Context context) {
        super(context);
        init(null, 0);
    }

    public WidgetListItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public WidgetListItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(@Nullable AttributeSet attrs, int defStyleAttr) {
        inflate(getContext(), R.layout.item_widget_list, this);
        mLogoIv = findViewById(R.id.iv_logo);
        mTitleTv = findViewById(R.id.tv_title);
        mSubtitleTv = findViewById(R.id.tv_subtitle);
        mActionTv = findViewById(R.id.tv_action);
        mArrowIv = findViewById(R.id.iv_arrow);
        mDividerV = findViewById(R.id.v_divider);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.WidgetListItem, defStyleAttr, 0);
            if (a.hasValue(R.styleable.WidgetListItem_logo)) {
                int resId = a.getResourceId(R.styleable.WidgetListItem_logo, -1);
                if (resId != -1) {
                    mLogoIv.setImageResource(resId);
                    mLogoIv.setVisibility(VISIBLE);
                }
            }
            if (a.hasValue(R.styleable.WidgetListItem_title)) {
                String title = a.getString(R.styleable.WidgetListItem_title);
                if (!TextUtils.isEmpty(title)) {
                    mTitleTv.setText(title);
                    mTitleTv.setVisibility(VISIBLE);
                }
            }
            if (a.hasValue(R.styleable.WidgetListItem_subtitle)) {
                String subtitle = a.getString(R.styleable.WidgetListItem_subtitle);
                if (!TextUtils.isEmpty(subtitle)) {
                    mSubtitleTv.setText(subtitle);
                    mSubtitleTv.setVisibility(VISIBLE);
                }
            }
            if (a.hasValue(R.styleable.WidgetListItem_action)) {
                String action = a.getString(R.styleable.WidgetListItem_action);
                if (!TextUtils.isEmpty(action)) {
                    mActionTv.setText(action);
                    mActionTv.setVisibility(VISIBLE);
                }
            }
            if (a.hasValue(R.styleable.WidgetListItem_clickable)) {
                boolean clickable = a.getBoolean(R.styleable.WidgetListItem_clickable, true);
                mArrowIv.setVisibility(clickable ? VISIBLE : GONE);
            }
            if (a.hasValue(R.styleable.WidgetListItem_has_divider)) {
                boolean hasDivider = a.getBoolean(R.styleable.WidgetListItem_has_divider, true);
                mDividerV.setVisibility(hasDivider ? VISIBLE : GONE);
            }
            a.recycle();
        }
    }

    public void setTitle(String title) {
        mTitleTv.setText(title);
        mTitleTv.setVisibility(VISIBLE);
    }

    public void setSubtitle(String subtitle) {
        mSubtitleTv.setText(subtitle);
        mSubtitleTv.setVisibility(VISIBLE);
    }

    public void setAction(String action) {
        mActionTv.setText(action);
        mActionTv.setVisibility(VISIBLE);
    }
}
