package com.ximsfei.skindemo.test;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.ximsfei.skindemo.R;

import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatSupportable;

public class SkinWebView extends WebView implements SkinCompatSupportable {
    public SkinWebView(Context context) {
        super(context);
        init();
    }

    public SkinWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SkinWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SkinWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public SkinWebView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
        init();
    }

    private void init() {
        setBackgroundColor(0);
        setBackgroundDrawable(SkinCompatResources.getInstance().getDrawable(R.drawable.picture));
    }

    @Override
    public void applySkin() {
        init();
    }
}
