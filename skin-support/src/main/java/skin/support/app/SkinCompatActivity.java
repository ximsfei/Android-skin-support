package skin.support.app;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import skin.support.SkinCompatManager;
import skin.support.content.res.SkinCompatResources;
import skin.support.observe.SkinObservable;
import skin.support.observe.SkinObserver;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;
import static skin.support.widget.SkinCompatHelper.checkResourceId;
import static skin.support.widget.SkinCompatThemeUtils.getWindowBackgroundResId;

/**
 * Created by ximsfei on 17-1-8.
 */

public class SkinCompatActivity extends AppCompatActivity implements SkinObserver {

    private SkinCompatDelegate mSkinDelegate;
    private int mWindowBackgroundResId = INVALID_ID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), getSkinDelegate());
        super.onCreate(savedInstanceState);
        mWindowBackgroundResId = getWindowBackgroundResId(this);
        mWindowBackgroundResId = checkResourceId(mWindowBackgroundResId);
    }

    @NonNull
    public SkinCompatDelegate getSkinDelegate() {
        if (mSkinDelegate == null) {
            mSkinDelegate = SkinCompatDelegate.create(this);
        }
        return mSkinDelegate;
    }

    @Override
    protected void onResume() {
        super.onResume();
        SkinCompatManager.getInstance().addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinCompatManager.getInstance().deleteObserver(this);
    }

    @Override
    public void updateSkin(SkinObservable observable, Object o) {
        getSkinDelegate().applySkin();
        if (mWindowBackgroundResId != INVALID_ID) {
            getWindow().setBackgroundDrawable(new ColorDrawable(
                    SkinCompatResources.getInstance().getColor(mWindowBackgroundResId)));
        }
    }
}
