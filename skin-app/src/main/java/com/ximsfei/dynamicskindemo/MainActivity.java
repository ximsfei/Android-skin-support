package com.ximsfei.dynamicskindemo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCompatActivity;
import skin.support.utils.SkinLog;

/**
 * Created by ximsfei on 2017/1/9.
 */

public class MainActivity extends SkinCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkinCompatManager.getInstance().loadSkin("new.skin", new SkinCompatManager.SkinLoaderListener() {
                    @Override
                    public void onStart() {
                        SkinLog.d("onStart");
                    }

                    @Override
                    public void onSuccess() {
                        SkinLog.d("onSuccess");
                    }

                    @Override
                    public void onFailed(String errMsg) {
                        SkinLog.d("onFailed");
                    }
                });
            }
        });

        findViewById(R.id.text1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkinCompatManager.getInstance().restoreDefaultTheme();
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
