package skin.support.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ximsfei on 2017/1/13.
 */

public interface SkinLayoutInflater {
    View createView(final String name, @NonNull Context context, @NonNull AttributeSet attrs);
}
