package skin.support.mobile.demo.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class CommonUtils {
    public static void jumpUrl(Activity activity, String urlStr) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri url = Uri.parse(urlStr);
        intent.setData(url);
        activity.startActivity(intent);
    }
}
