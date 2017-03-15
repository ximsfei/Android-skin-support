package skin.support.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import skin.support.widget.SkinAutoFrameLayout;
import skin.support.widget.SkinAutoLinearLayout;
import skin.support.widget.SkinAutoRelativeLayout;

/**
 * Created by pengfengwang on 2017/3/15.
 */

public class SkinHookAutoLayoutViewInflater implements SkinLayoutInflater {
    @Override
    public View createView(@NonNull Context context, final String name, @NonNull AttributeSet attrs) {
        View view = null;
        switch (name) {
            case "LinearLayout":
                view = new SkinAutoLinearLayout(context, attrs);
                break;
            case "RelativeLayout":
                view = new SkinAutoRelativeLayout(context, attrs);
                break;
            case "FrameLayout":
                view = new SkinAutoFrameLayout(context, attrs);
        }
        return view;
    }
}