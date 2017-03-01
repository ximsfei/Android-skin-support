package skin.support.design.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.AttributeSet;

import skin.support.widget.SkinCompatSupportable;

/**
 * Created by ximsfei on 17-3-1.
 */

public class SkinCompatBottomNavigationView extends BottomNavigationView implements SkinCompatSupportable {
    public SkinCompatBottomNavigationView(@NonNull Context context) {
        this(context, null);
    }

    public SkinCompatBottomNavigationView(@NonNull Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinCompatBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void applySkin() {
    }
}
