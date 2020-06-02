package skin.support.view;

import android.os.Build;
import android.view.View;

public class ViewCompat {

    /**
     * Returns whether the provided view has an attached {@link View.OnClickListener}.
     *
     * @return true if there is a listener, false if there is none.
     */
    public static boolean hasOnClickListeners(View view) {
        if (Build.VERSION.SDK_INT >= 15) {
            return view.hasOnClickListeners();
        }
        return false;
    }
}
