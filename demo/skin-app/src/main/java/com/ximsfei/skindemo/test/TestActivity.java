package com.ximsfei.skindemo.test;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ximsfei.skindemo.BaseActivity;
import com.ximsfei.skindemo.R;

public class TestActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initToolbar();
    }
}
