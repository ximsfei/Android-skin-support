package com.ximsfei.skin.sample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.ximsfei.skin.sample.R;
import com.ximsfei.skin.sample.base.BaseActivity;
import com.ximsfei.skin.sample.base.TabFragmentPagerAdapter;
import com.ximsfei.skin.sample.fragment.ChangeLogFragment;
import com.ximsfei.skin.sample.fragment.ComponentsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private TabFragmentPagerAdapter mTabFragmentPagerAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("首页");
        mToolbar.setSubtitle("Components");
        initBottomMenu();
        configFragments();
    }

    private void configFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(new ComponentsFragment());
        list.add(new ChangeLogFragment());
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager(), list));
        List<String> listTitle = new ArrayList<>();
        listTitle.add("Components");
        listTitle.add("ChangeLog");
        mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), list, listTitle);
        viewPager.setAdapter(mTabFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mToolbar.setSubtitle(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
