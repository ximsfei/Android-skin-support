package com.ximsfei.skindemo.test;

import android.os.Bundle;

import androidx.annotation.Nullable;
import skin.support.annotation.Skindisable;

@Skindisable
public class SkindisableTestActivity extends TestActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
