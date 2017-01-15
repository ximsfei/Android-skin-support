package skin.support.design.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import skin.support.app.SkinLayoutInflater;
import skin.support.design.widget.SkinCompatAppBarLayout;
import skin.support.design.widget.SkinCompatNavigationView;
import skin.support.design.widget.SkinCompatTabLayout;

/**
 * Created by ximsfei on 2017/1/13.
 */
public class SkinMaterialViewInflater implements SkinLayoutInflater {
    @Override
    public View createView(@NonNull Context context, final String name, @NonNull AttributeSet attrs) {
        View view = null;
        switch (name) {
            case "android.support.design.widget.AppBarLayout":
                view = new SkinCompatAppBarLayout(context, attrs);
                break;
            case "android.support.design.widget.TabLayout":
                view = new SkinCompatTabLayout(context, attrs);
                break;
            case "android.support.design.widget.NavigationView":
                view = new SkinCompatNavigationView(context, attrs);
                break;
        }
        return view;
    }
}
