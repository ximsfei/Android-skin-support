package skin.support.design.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import skin.support.app.SkinLayoutInflater;
import skin.support.design.widget.SkinMaterialAppBarLayout;
import skin.support.design.widget.SkinMaterialBottomNavigationView;
import skin.support.design.widget.SkinMaterialCollapsingToolbarLayout;
import skin.support.design.widget.SkinMaterialCoordinatorLayout;
import skin.support.design.widget.SkinMaterialFloatingActionButton;
import skin.support.design.widget.SkinMaterialNavigationView;
import skin.support.design.widget.SkinMaterialTabLayout;
import skin.support.design.widget.SkinMaterialTextInputEditText;
import skin.support.design.widget.SkinMaterialTextInputLayout;

/**
 * Created by ximsfei on 2017/1/13.
 */
public class SkinMaterialViewInflater implements SkinLayoutInflater {
    @Override
    public View createView(@NonNull Context context, final String name, @NonNull AttributeSet attrs) {
        View view = null;
        if (!name.startsWith("android.support.design.widget.")) {
            return null;
        }
        switch (name) {
            case "android.support.design.widget.AppBarLayout":
                view = new SkinMaterialAppBarLayout(context, attrs);
                break;
            case "android.support.design.widget.TabLayout":
                view = new SkinMaterialTabLayout(context, attrs);
                break;
            case "android.support.design.widget.TextInputLayout":
                view = new SkinMaterialTextInputLayout(context, attrs);
                break;
            case "android.support.design.widget.TextInputEditText":
                view = new SkinMaterialTextInputEditText(context, attrs);
                break;
            case "android.support.design.widget.NavigationView":
                view = new SkinMaterialNavigationView(context, attrs);
                break;
            case "android.support.design.widget.FloatingActionButton":
                view = new SkinMaterialFloatingActionButton(context, attrs);
                break;
            case "android.support.design.widget.BottomNavigationView":
                view = new SkinMaterialBottomNavigationView(context, attrs);
                break;
            case "android.support.design.widget.CoordinatorLayout":
                view = new SkinMaterialCoordinatorLayout(context, attrs);
                break;
            case "android.support.design.widget.CollapsingToolbarLayout":
                view = new SkinMaterialCollapsingToolbarLayout(context, attrs);
                break;
            default:
                break;
        }
        return view;
    }
}
