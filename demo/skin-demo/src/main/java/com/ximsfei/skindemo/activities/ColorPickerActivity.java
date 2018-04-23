package com.ximsfei.skindemo.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.ximsfei.skindemo.R;

import skin.support.SkinCompatManager;
import skin.support.content.res.SkinCompatColorManager;
import skin.support.content.res.SkinCompatResources;

public class ColorPickerActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("颜色选择");
        mToolbar.setSubtitle("文字颜色");
        SeekBar textColorPrimarySeekBar = findViewById(R.id.textColorPrimarySeekBar);
        textColorPrimarySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String color = Integer.toHexString(0xffffff * progress / 100);
                SkinCompatColorManager.get().addColorState(R.color.textColorPrimary, color);
                SkinCompatColorManager.get().addColorState(R.color.navigation_item_tint,
                        new SkinCompatColorManager.ColorBuilder()
                                .setColorPressed(color)
                                .setColorSelected(color)
                                .setColorChecked(color)
                                .setColorDefault(Integer.toHexString(SkinCompatResources.getColor(ColorPickerActivity.this, R.color.colorDefault)))
                                .build());
                SkinCompatManager.getInstance().previewSkinInCurActivity(ColorPickerActivity.this);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Button clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinCompatColorManager.get().clearColors();
                SkinCompatColorManager.get().applyColors();
            }
        });
        Button apply = findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinCompatColorManager.get().applyColors();
            }
        });
    }
}
