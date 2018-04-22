package com.ximsfei.skindemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ximsfei.skindemo.loader.CustomSDCardLoader;

import skin.support.SkinCompatManager;

public class BaseActivity extends AppCompatActivity {
    protected void initBottomMenu() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_default:
                        SkinCompatManager.getInstance().restoreDefaultTheme();
                        return true;
                    case R.id.menu_built_in:
                        SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
                        return true;
                    case R.id.menu_plug_in:
                        SkinCompatManager.getInstance().loadSkin("night.skin", SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS);
                        return true;
                    case R.id.menu_sdcard:
                        SkinCompatManager.getInstance().loadSkin("night.skin", CustomSDCardLoader.SKIN_LOADER_STRATEGY_SDCARD);
                        return true;
                    case R.id.menu_color_picker:
                        Intent intent = new Intent();
                        intent.setClass(BaseActivity.this, ColorPickerActivity.class);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}
