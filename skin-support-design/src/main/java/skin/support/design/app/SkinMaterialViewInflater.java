package skin.support.design.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import skin.support.app.SkinLayoutInflater;
import skin.support.design.widget.SkinCompatAppBarLayout;
import skin.support.design.widget.SkinCompatBottomNavigationView;
import skin.support.design.widget.SkinCompatFloatingActionButton;
import skin.support.design.widget.SkinCompatNavigationView;
import skin.support.design.widget.SkinCompatTabLayout;
import skin.support.design.widget.SkinCompatTextInputLayout;

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
            case "android.support.design.widget.TextInputLayout":
                view = new SkinCompatTextInputLayout(context, attrs);
                break;
            case "android.support.design.widget.NavigationView":
                view = new SkinCompatNavigationView(context, attrs);
                break;
            case "android.support.design.widget.FloatingActionButton":
                view = new SkinCompatFloatingActionButton(context, attrs);
                break;
            case "android.support.design.widget.BottomNavigationView":
                view = new SkinCompatBottomNavigationView(context, attrs);
                break;
        }
        return view;
    }
}
