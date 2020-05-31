package skin.support.mobile.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import skin.support.SkinCompatManager;
import skin.support.mobile.demo.R;
import skin.support.mobile.demo.activity.AboutActivity;
import skin.support.mobile.demo.activity.ColorPickerActivity;
import skin.support.mobile.demo.fragment.base.BaseFragment;
import skin.support.mobile.demo.util.AppTools;
import skin.support.mobile.demo.util.Constants;
import skin.support.mobile.demo.widget.WidgetListItem;
import skin.support.utils.SkinPreference;
import skin.support.utils.Slog;

public class MineFragment extends BaseFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private WidgetListItem mAboutWli;
    private WidgetListItem mColorPickerWli;
    private RadioGroup mSkinRg;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 换肤
        mSkinRg = view.findViewById(R.id.rg_skin);
        mSkinRg.setOnCheckedChangeListener(this);
        initRg();

        // 自选颜色
        mColorPickerWli = view.findViewById(R.id.wli_color_picker);
        mColorPickerWli.setOnClickListener(this);
        // 关于
        mAboutWli = view.findViewById(R.id.wli_about);
        mAboutWli.setOnClickListener(this);
        String versionName = AppTools.getVersionName(view.getContext());
        if (!TextUtils.isEmpty(versionName)) {
            mAboutWli.setAction(String.format(getString(R.string.about_version_name), versionName));
        }
    }

    private void initRg() {
        String skinName = SkinPreference.getInstance().getSkinName();
        int checkedId = R.id.rb_skin_default;
        if (!TextUtils.isEmpty(skinName)) {
            switch (skinName) {
                case Constants.SKIN_NAME.BLACK:
                    checkedId = R.id.rb_skin_black;
                    break;
                case Constants.SKIN_NAME.BLUE:
                    checkedId = R.id.rb_skin_blue;
                    break;
                case Constants.SKIN_NAME.GREEN:
                    checkedId = R.id.rb_skin_green;
                    break;
                case Constants.SKIN_NAME.ORANGE:
                    checkedId = R.id.rb_skin_orange;
                    break;
                case Constants.SKIN_NAME.PINK:
                    checkedId = R.id.rb_skin_pink;
                    break;
                case Constants.SKIN_NAME.RED:
                    checkedId = R.id.rb_skin_red;
                    break;
                case Constants.SKIN_NAME.WHITE:
                    checkedId = R.id.rb_skin_white;
                    break;
                case Constants.SKIN_NAME.YELLOW:
                    checkedId = R.id.rb_skin_yellow;
                    break;
            }
        }
        mSkinRg.check(checkedId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wli_about:
                startActivity(new Intent(getContext(), AboutActivity.class));
                break;
            case R.id.wli_color_picker:
                startActivity(new Intent(getContext(), ColorPickerActivity.class));
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Slog.i("MineFragment", "checkId: " + checkedId);
        switch (checkedId) {
            case R.id.rb_skin_default:
                SkinCompatManager.getInstance().restoreDefaultTheme();
                break;
            case R.id.rb_skin_yellow:
                SkinCompatManager.getInstance()
                        .loadSkin(Constants.SKIN_NAME.YELLOW, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
                break;
            case R.id.rb_skin_red:
                SkinCompatManager.getInstance()
                        .loadSkin(Constants.SKIN_NAME.RED, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
                break;
            case R.id.rb_skin_pink:
                SkinCompatManager.getInstance()
                        .loadSkin(Constants.SKIN_NAME.PINK, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
                break;
            case R.id.rb_skin_black:
                SkinCompatManager.getInstance()
                        .loadSkin(Constants.SKIN_NAME.BLACK, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
                break;
            case R.id.rb_skin_white:
                SkinCompatManager.getInstance()
                        .loadSkin(Constants.SKIN_NAME.WHITE, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
                break;
            case R.id.rb_skin_blue:
                SkinCompatManager.getInstance()
                        .loadSkin(Constants.SKIN_NAME.BLUE, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
                break;
            case R.id.rb_skin_green:
                SkinCompatManager.getInstance()
                        .loadSkin(Constants.SKIN_NAME.GREEN, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
                break;
            case R.id.rb_skin_orange:
                SkinCompatManager.getInstance()
                        .loadSkin(Constants.SKIN_NAME.ORANGE, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
                break;
        }
    }
}
