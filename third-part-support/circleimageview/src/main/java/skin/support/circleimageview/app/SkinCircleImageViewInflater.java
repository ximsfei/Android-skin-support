package skin.support.circleimageview.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import skin.support.app.SkinLayoutInflater;
import skin.support.circleimageview.widget.SkinCompatCircleImageView;

/**
 * Created by ximsfei on 2017/3/5.
 */

public class SkinCircleImageViewInflater implements SkinLayoutInflater {
    @Override
    public View createView(Context context, final String name, AttributeSet attrs) {
        View view = null;
        switch (name) {
            case "de.hdodenhof.circleimageview.CircleImageView":
                view = new SkinCompatCircleImageView(context, attrs);
                break;
        }
        return view;
    }
}
