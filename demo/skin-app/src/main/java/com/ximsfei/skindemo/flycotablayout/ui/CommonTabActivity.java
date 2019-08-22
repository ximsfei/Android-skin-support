package com.ximsfei.skindemo.flycotablayout.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.utils.UnreadMsgUtils;
import com.flyco.tablayout.widget.MsgView;
import com.ximsfei.skindemo.R;
import com.ximsfei.skindemo.flycotablayout.entity.TabEntity;
import com.ximsfei.skindemo.flycotablayout.utils.ViewFindUtils;

import java.util.ArrayList;
import java.util.Random;

import skin.support.flycotablayout.widget.SkinMsgView;

public class CommonTabActivity extends FlycoActivity {
    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<Fragment> mFragments2 = new ArrayList<>();

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private View mDecorView;
    private ViewPager mViewPager;
    private CommonTabLayout mTabLayout_1;
    private CommonTabLayout mTabLayout_2;
    private CommonTabLayout mTabLayout_3;
    private CommonTabLayout mTabLayout_4;
    private CommonTabLayout mTabLayout_5;
    private CommonTabLayout mTabLayout_6;
    private CommonTabLayout mTabLayout_7;
    private CommonTabLayout mTabLayout_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_tab);

        for (String title : mTitles) {
            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
            mFragments2.add(SimpleCardFragment.getInstance("Switch Fragment " + title));
        }


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mDecorView = getWindow().getDecorView();
        mViewPager = ViewFindUtils.find(mDecorView, R.id.vp_2);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        /** with nothing */
        mTabLayout_1 = ViewFindUtils.find(mDecorView, R.id.t2_1);
        /** with ViewPager */
        mTabLayout_2 = ViewFindUtils.find(mDecorView, R.id.t2_2);
        /** with Fragments */
        mTabLayout_3 = ViewFindUtils.find(mDecorView, R.id.t2_3);
        /** indicator固定宽度 */
        mTabLayout_4 = ViewFindUtils.find(mDecorView, R.id.t2_4);
        /** indicator固定宽度 */
        mTabLayout_5 = ViewFindUtils.find(mDecorView, R.id.t2_5);
        /** indicator矩形圆角 */
        mTabLayout_6 = ViewFindUtils.find(mDecorView, R.id.t2_6);
        /** indicator三角形 */
        mTabLayout_7 = ViewFindUtils.find(mDecorView, R.id.t2_7);
        /** indicator圆角色块 */
        mTabLayout_8 = ViewFindUtils.find(mDecorView, R.id.t2_8);

        mTabLayout_1.setTabData(mTabEntities);
        tl_2();
        mTabLayout_3.setTabData(mTabEntities, this, R.id.fl_change, mFragments2);
        mTabLayout_4.setTabData(mTabEntities);
        mTabLayout_5.setTabData(mTabEntities);
        mTabLayout_6.setTabData(mTabEntities);
        mTabLayout_7.setTabData(mTabEntities);
        mTabLayout_8.setTabData(mTabEntities);

        mTabLayout_3.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mTabLayout_1.setCurrentTab(position);
                mTabLayout_2.setCurrentTab(position);
                mTabLayout_4.setCurrentTab(position);
                mTabLayout_5.setCurrentTab(position);
                mTabLayout_6.setCurrentTab(position);
                mTabLayout_7.setCurrentTab(position);
                mTabLayout_8.setCurrentTab(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mTabLayout_8.setCurrentTab(2);
        mTabLayout_3.setCurrentTab(1);

        //显示未读红点
        mTabLayout_1.showDot(2);
        MsgView rtv_1_2 = mTabLayout_1.getMsgView(2);
        if (rtv_1_2 != null) {
            if (rtv_1_2 instanceof SkinMsgView) {
                ((SkinMsgView) rtv_1_2).setBackgroundColorResource(R.color.msg_background_color);
            }
        }
        mTabLayout_3.showDot(1);
        MsgView rtv_3_1 = mTabLayout_3.getMsgView(1);
        if (rtv_3_1 != null) {
            if (rtv_3_1 instanceof SkinMsgView) {
                ((SkinMsgView) rtv_3_1).setBackgroundColorResource(R.color.msg_background_color);
            }
        }
        mTabLayout_4.showDot(1);
        MsgView rtv_4_1 = mTabLayout_4.getMsgView(1);
        if (rtv_4_1 != null) {
            if (rtv_4_1 instanceof SkinMsgView) {
                ((SkinMsgView) rtv_4_1).setBackgroundColorResource(R.color.msg_background_color);
            }
        }

        //两位数
        mTabLayout_2.showMsg(0, 55);
        MsgView rtv_2_0 = mTabLayout_2.getMsgView(0);
        if (rtv_2_0 != null) {
            if (rtv_2_0 instanceof SkinMsgView) {
                ((SkinMsgView) rtv_2_0).setBackgroundColorResource(R.color.msg_background_color);
            }
        }
        mTabLayout_2.setMsgMargin(0, -5, 5);

        //三位数
        mTabLayout_2.showMsg(1, 100);
        MsgView rtv_2_1 = mTabLayout_2.getMsgView(1);
        if (rtv_2_1 != null) {
            if (rtv_2_1 instanceof SkinMsgView) {
                ((SkinMsgView) rtv_2_1).setBackgroundColorResource(R.color.msg_background_color);
            }
        }
        mTabLayout_2.setMsgMargin(1, -5, 5);

        //设置未读消息红点
        mTabLayout_2.showDot(2);
        MsgView rtv_2_2 = mTabLayout_2.getMsgView(2);
        if (rtv_2_2 != null) {
            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
        }

        //设置未读消息背景
        mTabLayout_2.showMsg(3, 5);
        mTabLayout_2.setMsgMargin(3, 0, 5);
        MsgView rtv_2_3 = mTabLayout_2.getMsgView(3);
        if (rtv_2_3 != null) {
            if (rtv_2_3 instanceof SkinMsgView) {
                ((SkinMsgView) rtv_2_3).setBackgroundColorResource(R.color.msg_background_color);
                ((SkinMsgView) rtv_2_3).setStrokeColorResource(R.color.msg_stroke_color);
            } else {
                rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
            }
        }
    }

    Random mRandom = new Random();

    private void tl_2() {
        mTabLayout_2.setTabData(mTabEntities);
        mTabLayout_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    mTabLayout_2.showMsg(0, mRandom.nextInt(100) + 1);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout_2.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(1);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
