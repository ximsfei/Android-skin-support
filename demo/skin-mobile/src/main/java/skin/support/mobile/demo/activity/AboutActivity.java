package skin.support.mobile.demo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;

import skin.support.mobile.demo.R;
import skin.support.mobile.demo.activity.base.BaseActivity;
import skin.support.mobile.demo.util.AppTools;

public class AboutActivity extends BaseActivity {
    private TextView mVersionNameTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mVersionNameTv = findViewById(R.id.tv_version_name);
        String versionName = AppTools.getVersionName(this);
        if (!TextUtils.isEmpty(versionName)) {
            mVersionNameTv.setText(String.format(getString(R.string.about_version_name), versionName));
        }
    }
}
