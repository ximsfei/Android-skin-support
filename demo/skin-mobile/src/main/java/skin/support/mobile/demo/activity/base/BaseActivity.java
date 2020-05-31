package skin.support.mobile.demo.activity.base;

import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.SkinAppCompatDelegateImpl;
import androidx.appcompat.widget.Toolbar;

import skin.support.content.res.SkinCompatResources;
import skin.support.mobile.demo.R;
import skin.support.mobile.demo.util.SkinStatusBarUtils;
import skin.support.widget.SkinCompatSupportable;

public abstract class BaseActivity extends AppCompatActivity implements SkinCompatSupportable {
    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);
    }

    protected void initToolbar(boolean enableHomeAsUp) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        setSupportActionBar(toolbar);
        if (enableHomeAsUp) {
            toolbar.setNavigationIcon(R.drawable.icon_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    @Override
    public void applySkin() {
        updateStatusBarColor();
    }

    private void updateStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(SkinCompatResources.getColor(this, R.color.colorPrimary));
        }
        // 修改状态栏字体颜色
        boolean useDarkStatusBar = getResources().getBoolean(R.bool.use_dark_status);
        int resId = SkinCompatResources.getInstance().getTargetResId(this, R.bool.use_dark_status);
        if (resId != 0) {
            useDarkStatusBar = SkinCompatResources.getInstance().getSkinResources().getBoolean(resId);
        }
        if (useDarkStatusBar) {
            SkinStatusBarUtils.setStatusBarDarkMode(this);
        } else {
            SkinStatusBarUtils.setStatusBarLightMode(this);
        }
    }
}
