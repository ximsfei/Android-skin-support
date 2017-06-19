package com.ximsfei.skindemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import skin.support.SkinCompatManager;
import skin.support.utils.SkinPreference;

/**
 * Created by pengfengwang on 2017/6/19.
 */

public class ConstraintLayoutActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);

        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
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
}
