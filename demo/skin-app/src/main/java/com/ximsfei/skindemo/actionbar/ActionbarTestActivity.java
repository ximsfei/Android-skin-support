package com.ximsfei.skindemo.actionbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ximsfei.skindemo.R;

import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatSupportable;

/**
 * Created by Frankmao on 2018/2/1.
 */

public class ActionbarTestActivity extends AppCompatActivity implements SkinCompatSupportable {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);

    }

    @Override
    public void applySkin() {
        getSupportActionBar().setBackgroundDrawable(SkinCompatResources.getInstance().getDrawable(R.drawable.bg_bar));
    }
}
