package com.ximsfei.skindemo.flycotablayout.ui;

import android.text.TextUtils;
import android.view.View;

import com.ximsfei.skindemo.R;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCompatActivity;
import skin.support.utils.SkinPreference;

/**
 * Created by pengfengwang on 2017/3/9.
 */

public class FlycoActivity extends SkinCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
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
}
