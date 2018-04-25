package com.ximsfei.skin.sample.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ximsfei.skin.sample.activities.ColorPickerActivity;
import com.ximsfei.skin.sample.R;
import com.ximsfei.skin.sample.loader.CustomSDCardLoader;

import skin.support.SkinCompatManager;
import skin.support.utils.SkinPreference;

public class BaseActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;

    protected void initBottomMenu() {
        mBottomNavigationView = findViewById(R.id.bottom_menu);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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

    @Override
    protected void onResume() {
        super.onResume();
        if (mBottomNavigationView != null) {
            int strategy = SkinPreference.getInstance().getSkinStrategy();
            switch (strategy) {
                case SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN:
                    mBottomNavigationView.setSelectedItemId(R.id.menu_built_in);
                    break;
                case SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS:
                    mBottomNavigationView.setSelectedItemId(R.id.menu_plug_in);
                    break;
                case CustomSDCardLoader.SKIN_LOADER_STRATEGY_SDCARD:
                    mBottomNavigationView.setSelectedItemId(R.id.menu_sdcard);
                    break;
                default:
                    mBottomNavigationView.setSelectedItemId(R.id.menu_default);
                    break;
            }
        }
    }
}
