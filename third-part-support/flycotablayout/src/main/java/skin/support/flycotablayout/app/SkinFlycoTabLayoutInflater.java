package skin.support.flycotablayout.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import skin.support.app.SkinLayoutInflater;

/**
 * Created by ximsf on 2017/3/8.
 */

public class SkinFlycoTabLayoutInflater implements SkinLayoutInflater {
    @Override
    public View createView(@NonNull Context context, String name, @NonNull AttributeSet attrs) {
        View view = null;
        switch (name) {
//            case "de.hdodenhof.circleimageview.CircleImageView":
//                view = new SkinCompatCircleImageView(context, attrs);
//                break;
        }
        return view;
    }
}
