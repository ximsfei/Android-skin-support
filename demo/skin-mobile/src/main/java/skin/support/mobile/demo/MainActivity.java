package skin.support.mobile.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import skin.support.mobile.demo.fragment.base.BaseFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMenuBnv;
    private ViewPager2 mContainerVp;
    private ArrayList<Fragment> mFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMenuBnv = findViewById(R.id.bnv_menu);
        mContainerVp = findViewById(R.id.vp_container);
        initViews();
    }

    private void initViews() {
        mFragments = new ArrayList<>();
        mFragments.add(new BaseFragment());
        mFragments.add(new BaseFragment());
        mFragments.add(new BaseFragment());
        mContainerVp.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getItemCount() {
                return mFragments.size();
            }
        });
        mContainerVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                mMenuBnv.getMenu().getItem(position).setChecked(true);
            }
        });
        mMenuBnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bnv_home:
                        mContainerVp.setCurrentItem(0);
                        return true;
                    case R.id.bnv_discovery:
                        mContainerVp.setCurrentItem(1);
                        return true;
                    case R.id.bnv_mine:
                        mContainerVp.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });
    }
}
