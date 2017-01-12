package skin.support.app;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.VectorEnabledTintResources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.List;

import skin.support.utils.SkinLog;
import skin.support.widget.SkinCompatHelper;

/**
 * Created by ximsfei on 2017/1/9.
 */

public class SkinCompatDelegate implements LayoutInflaterFactory {
    private final AppCompatActivity mAppCompatActivity;
    private SkinCompatViewInflater mSkinCompatViewInflater;
    private List<SkinCompatHelper> mSkinHelpers = new ArrayList<>();

    private SkinCompatDelegate(AppCompatActivity appCompatActivity) {
        mAppCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = createView(parent, name, context, attrs);

        if (view == null) {
            return null;
        }
        if (view instanceof SkinCompatHelper) {
            mSkinHelpers.add((SkinCompatHelper) view);
        }

        return view;
    }

    public View createView(View parent, final String name, @NonNull Context context,
                           @NonNull AttributeSet attrs) {
        final boolean isPre21 = Build.VERSION.SDK_INT < 21;

        if (mSkinCompatViewInflater == null) {
            mSkinCompatViewInflater = new SkinCompatViewInflater();
        }

        // We only want the View to inherit its context if we're running pre-v21
        final boolean inheritContext = isPre21 && shouldInheritContext((ViewParent) parent);

        return mSkinCompatViewInflater.createView(parent, name, context, attrs, inheritContext,
                isPre21, /* Only read android:theme pre-L (L+ handles this anyway) */
                true, /* Read read app:theme as a fallback at all times for legacy reasons */
                VectorEnabledTintResources.shouldBeUsed() /* Only tint wrap the context if enabled */
        );
    }

    private boolean shouldInheritContext(ViewParent parent) {
        if (parent == null) {
            // The initial parent is null so just return false
            return false;
        }
        final View windowDecor = mAppCompatActivity.getWindow().getDecorView();
        while (true) {
            if (parent == null) {
                // Bingo. We've hit a view which has a null parent before being terminated from
                // the loop. This is (most probably) because it's the root view in an inflation
                // call, therefore we should inherit. This works as the inflated layout is only
                // added to the hierarchy at the end of the inflate() call.
                return true;
            } else if (parent == windowDecor || !(parent instanceof View)
                    || ViewCompat.isAttachedToWindow((View) parent)) {
                // We have either hit the window's decor view, a parent which isn't a View
                // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                // is currently added to the view hierarchy. This means that it has not be
                // inflated in the current inflate() call and we should not inherit the context.
                return false;
            }
            parent = parent.getParent();
        }
    }

    public static SkinCompatDelegate create(AppCompatActivity appCompatActivity) {
        return new SkinCompatDelegate(appCompatActivity);
    }

    public void applySkin() {
        if (mSkinHelpers != null || !mSkinHelpers.isEmpty()) {
            SkinLog.d("size - " + mSkinHelpers.size());
            for (SkinCompatHelper helper : mSkinHelpers) {
                helper.applySkin();
            }
        }
    }
}
