package skin.support.design.widget;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;

/**
 * Created by ximsfei on 17-3-2.
 */

public class SkinCompatCollapsingToolbarLayout extends CollapsingToolbarLayout {
    public SkinCompatCollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    public SkinCompatCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinCompatCollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
