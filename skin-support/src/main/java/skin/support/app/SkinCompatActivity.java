package skin.support.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import skin.support.SkinCompatManager;
import skin.support.observe.SkinObservable;
import skin.support.observe.SkinObserver;

/**
 * Created by ximsfei on 17-1-8.
 */

public class SkinCompatActivity extends AppCompatActivity implements SkinObserver {

    private SkinCompatDelegate mSkinDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), getSkinDelegate());
        super.onCreate(savedInstanceState);
        SkinCompatStatusBar.setWindowStatusBarColor(this);
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
        SkinCompatStatusBar.setWindowStatusBarColor(this);
    }
}
