package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

public class SkinAppCompatDelegateImpl extends AppCompatDelegate {
    private static Map<Activity, AppCompatDelegate> sDelegateMap = new HashMap<>();

    private final AppCompatDelegate mDelegate;
    private final Activity mActivity;

    public static AppCompatDelegate get(Activity activity, AppCompatCallback callback) {
        AppCompatDelegate delegate = sDelegateMap.get(activity);
        if (delegate == null) {
            delegate = new SkinAppCompatDelegateImpl(activity, callback);
            sDelegateMap.put(activity, delegate);
        }
        return delegate;
    }

    private SkinAppCompatDelegateImpl(Activity activity, AppCompatCallback callback) {
        mActivity = activity;
        mDelegate = AppCompatDelegate.create(activity, callback);
    }

    @Nullable
    @Override
    public ActionBar getSupportActionBar() {
        return mDelegate.getSupportActionBar();
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        mDelegate.setSupportActionBar(toolbar);
    }

    @Override
    public MenuInflater getMenuInflater() {
        return mDelegate.getMenuInflater();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mDelegate.onCreate(savedInstanceState);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        mDelegate.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        mDelegate.onConfigurationChanged(newConfig);
    }

    @Override
    public void onStart() {
        mDelegate.onStart();
    }

    @Override
    public void onStop() {
        mDelegate.onStop();
    }

    @Override
    public void onPostResume() {
        mDelegate.onPostResume();
    }

    @Nullable
    @Override
    public View findViewById(int id) {
        return mDelegate.findViewById(id);
    }

    @Override
    public void setContentView(View v) {
        mDelegate.setContentView(v);
    }

    @Override
    public void setContentView(int resId) {
        mDelegate.setContentView(resId);
    }

    @Override
    public void setContentView(View v, ViewGroup.LayoutParams lp) {
        mDelegate.setContentView(v, lp);
    }

    @Override
    public void addContentView(View v, ViewGroup.LayoutParams lp) {
        mDelegate.addContentView(v, lp);
    }

    @Override
    public void setTitle(@Nullable CharSequence title) {
        mDelegate.setTitle(title);
    }

    @Override
    public void invalidateOptionsMenu() {
        mDelegate.invalidateOptionsMenu();
    }

    @Override
    public void onDestroy() {
        mDelegate.onDestroy();
        sDelegateMap.remove(mActivity);
    }

    @Nullable
    @Override
    public ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
        return mDelegate.getDrawerToggleDelegate();
    }

    @Override
    public boolean requestWindowFeature(int featureId) {
        return mDelegate.requestWindowFeature(featureId);
    }

    @Override
    public boolean hasWindowFeature(int featureId) {
        return mDelegate.hasWindowFeature(featureId);
    }

    @Nullable
    @Override
    public ActionMode startSupportActionMode(@NonNull ActionMode.Callback callback) {
        return mDelegate.startSupportActionMode(callback);
    }

    @Override
    public void installViewFactory() {
        // ignore
    }

    @Override
    public View createView(@Nullable View parent, String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return mDelegate.createView(parent, name, context, attrs);
    }

    @Override
    public void setHandleNativeActionModesEnabled(boolean enabled) {
        mDelegate.setHandleNativeActionModesEnabled(enabled);
    }

    @Override
    public boolean isHandleNativeActionModesEnabled() {
        return mDelegate.isHandleNativeActionModesEnabled();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mDelegate.onSaveInstanceState(outState);
    }

    @Override
    public boolean applyDayNight() {
        return mDelegate.applyDayNight();
    }

    @Override
    public void setLocalNightMode(int mode) {
        mDelegate.setLocalNightMode(mode);
    }
}
