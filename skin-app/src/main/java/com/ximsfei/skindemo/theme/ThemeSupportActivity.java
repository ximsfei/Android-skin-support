package com.ximsfei.skindemo.theme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.ximsfei.skindemo.R;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCompatActivity;
import skin.support.utils.SkinPreference;

/**
 * Created by pengfengwang on 2017/3/11.
 */

public class ThemeSupportActivity extends SkinCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_support);
//        initToolbar();
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(SkinPreference.getInstance().getSkinName())) {
                    SkinCompatManager.getInstance().loadSkin("night.skin", null);
                } else {
                    SkinCompatManager.getInstance().restoreDefaultTheme();
                }
            }
        });
    }

    protected void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Title");
        toolbar.setSubtitle("Subtitle");
        toolbar.setNavigationIcon(R.drawable.ic_settings_36dp);
    }
}
