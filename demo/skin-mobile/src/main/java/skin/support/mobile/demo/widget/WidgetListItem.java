package skin.support.mobile.demo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import skin.support.mobile.demo.R;

public class WidgetListItem extends LinearLayout {
    private ImageView mLogoIv;
    private TextView mTitleTv;
    private TextView mDescTv;

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
        mDescTv = findViewById(R.id.tv_desc);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.WidgetListItem, defStyleAttr, 0);
            if (a.hasValue(R.styleable.WidgetListItem_logo)) {
                int resId = a.getResourceId(R.styleable.WidgetListItem_logo, -1);
                if (resId != -1) {
                    mLogoIv.setImageResource(resId);
                }
            }
            if (a.hasValue(R.styleable.WidgetListItem_title)) {
                String title = a.getString(R.styleable.WidgetListItem_title);
                if (!TextUtils.isEmpty(title)) {
                    mTitleTv.setText(title);
                    mTitleTv.setVisibility(VISIBLE);
                }
            }
            if (a.hasValue(R.styleable.WidgetListItem_desc)) {
                String desc = a.getString(R.styleable.WidgetListItem_desc);
                if (!TextUtils.isEmpty(desc)) {
                    mDescTv.setText(desc);
                    mDescTv.setVisibility(VISIBLE);
                }
            }
            a.recycle();
        }
    }
}
