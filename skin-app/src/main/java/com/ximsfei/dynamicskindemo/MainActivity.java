package com.ximsfei.dynamicskindemo;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckedTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.ximsfei.dynamicskindemo.tab.FirstFragment;
import com.ximsfei.dynamicskindemo.tab.LastFragment;
import com.ximsfei.dynamicskindemo.tab.MiddleFragment;
import com.ximsfei.dynamicskindemo.tab.TabFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCompatActivity;
import skin.support.utils.SkinLog;
import skin.support.utils.SkinPreference;

/**
 * Created by ximsfei on 2017/1/9.
 */

public class MainActivity extends SkinCompatActivity {
//public class MainActivity extends AppCompatActivity {
    private TabFragmentPagerAdapter mTabFragmentPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        configFragments();
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(SkinPreference.getInstance().getSkinPath())) {
                    SkinCompatManager.getInstance().loadSkin("red.skin", null);
                } else {
                    SkinCompatManager.getInstance().restoreDefaultTheme();
                }
            }
        });
//        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SkinCompatManager.getInstance().loadSkin("red.skin", new SkinCompatManager.SkinLoaderListener() {
//                    @Override
//                    public void onStart() {
//                        SkinLog.d("onStart");
//                    }
//
//                    @Override
//                    public void onSuccess() {
//                        SkinLog.d("onSuccess");
//                    }
//
//                    @Override
//                    public void onFailed(String errMsg) {
//                        SkinLog.d("onFailed");
//                    }
//                });
//            }
//        });
//
//        findViewById(R.id.text1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SkinCompatManager.getInstance().restoreDefaultTheme();
//            }
//        });
//        findViewById(R.id.image_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Image Button", Toast.LENGTH_SHORT).show();
//            }
//        });
//        findViewById(R.id.checked_text_view).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                CheckedTextView checkedTextView = (CheckedTextView) v;
//                checkedTextView.toggle();
////                checkedMap.put(position, checkedTextView.isChecked());
//            }
//        });
//        MultiAutoCompleteTextView autoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.auto);
//        String[] arr = {"aa", "aab", "aac"};
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
//        autoCompleteTextView.setAdapter(arrayAdapter);
//        autoCompleteTextView.setThreshold(1);
//        autoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private void configFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(new FirstFragment());
        list.add(new MiddleFragment());
        list.add(new LastFragment());
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager(), list));
        List<String> listTitle = new ArrayList<>();
        listTitle.add("个性推荐");
        listTitle.add("歌单");
        listTitle.add("电台");
        mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), list, listTitle);
        viewPager.setAdapter(mTabFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void setWindowStatusBarColor() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(android.R.color.darker_gray));
                //window.setNavigationBarColor(color);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearWindowStatusBarColor() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
