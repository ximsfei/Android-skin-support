package skin.support.app;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import skin.support.widget.SkinCompatResources;

/**
 * Created by ximsfei on 2017/1/12.
 */

public class SkinCompatStatusBar {
    public static void setWindowStatusBarColor(Activity activity) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int color = SkinCompatResources.getInstance().getStatusBarColor();
                if (color == 0) return;
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(color);
                //window.setNavigationBarColor(color);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setWindowStatusBarColor(Dialog dialog) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int color = SkinCompatResources.getInstance().getStatusBarColor();
                if (color == 0) return;
                Window window = dialog.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(color);
                //window.setNavigationBarColor(color);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
