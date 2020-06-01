package skin.support.mobile.demo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import skin.support.mobile.demo.R;
import skin.support.mobile.demo.activity.base.BaseActivity;
import skin.support.mobile.demo.util.AppTools;
import skin.support.mobile.demo.util.CommonUtils;
import skin.support.mobile.demo.util.Constants;
import skin.support.mobile.demo.widget.WidgetListItem;

public class AboutActivity extends BaseActivity implements View.OnClickListener {
    private TextView mVersionNameTv;
    private WidgetListItem mFeatureWli;
    private WidgetListItem mLicenseWli;
    private WidgetListItem mUpdateVersionWli;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initToolbar(true);
        mVersionNameTv = findViewById(R.id.tv_version_name);
        String versionName = AppTools.getVersionName(this);
        if (!TextUtils.isEmpty(versionName)) {
            mVersionNameTv.setText(String.format(getString(R.string.about_version_name), versionName));
        }
        mFeatureWli = findViewById(R.id.wli_feature);
        mFeatureWli.setOnClickListener(this);
        mLicenseWli = findViewById(R.id.wli_license);
        mLicenseWli.setOnClickListener(this);
        mUpdateVersionWli = findViewById(R.id.wli_update_version);
        mUpdateVersionWli.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wli_feature:
                CommonUtils.jumpUrl(this, Constants.URL.FEATURE_URL);
                break;
            case R.id.wli_license:
                CommonUtils.jumpUrl(this, Constants.URL.LICENSE_URL);
                break;
            case R.id.wli_update_version:
                CommonUtils.jumpUrl(this, Constants.URL.UPDATE_VERSION_URL);
                break;
        }
    }
}
