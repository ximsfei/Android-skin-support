package skin.support.mobile.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import skin.support.mobile.demo.R;
import skin.support.mobile.demo.activity.AboutActivity;
import skin.support.mobile.demo.fragment.base.BaseFragment;
import skin.support.mobile.demo.util.AppTools;
import skin.support.mobile.demo.widget.WidgetListItem;

public class MineFragment extends BaseFragment implements View.OnClickListener {
    private WidgetListItem mAboutWli;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAboutWli = view.findViewById(R.id.wli_about);
        mAboutWli.setOnClickListener(this);
        String versionName = AppTools.getVersionName(view.getContext());
        if (!TextUtils.isEmpty(versionName)) {
            mAboutWli.setAction(String.format(getString(R.string.about_version_name), versionName));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wli_about:
                startActivity(new Intent(getContext(), AboutActivity.class));
                break;
        }
    }
}
