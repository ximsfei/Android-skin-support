package skin.support.flycotablayout.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import skin.support.app.SkinLayoutInflater;
import skin.support.flycotablayout.widget.SkinSlidingTabLayout;

/**
 * Created by ximsf on 2017/3/8.
 */

public class SkinFlycoTabLayoutInflater implements SkinLayoutInflater {
    @Override
    public View createView(@NonNull Context context, String name, @NonNull AttributeSet attrs) {
        View view = null;
        switch (name) {
            case "com.flyco.tablayout.SlidingTabLayout":
                view = new SkinSlidingTabLayout(context, attrs);
                break;
        }
        return view;
    }
}
