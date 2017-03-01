package com.ximsfei.dynamicskindemo;

import android.support.v7.widget.Toolbar;

import skin.support.app.SkinCompatActivity;

/**
 * Created by ximsfei on 17-3-1.
 */

public class BaseActivity extends SkinCompatActivity {
    protected void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Title");
        toolbar.setSubtitle("Subtitle");
        toolbar.setNavigationIcon(R.drawable.actionbar_discover_normal);
    }
}
