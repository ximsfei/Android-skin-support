package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.os.BuildCompat;
import android.view.Window;

import java.util.Map;
import java.util.WeakHashMap;

public class SkinAppCompatDelegateImpl {
    private static Map<Activity, AppCompatDelegate> sDelegateMap = new WeakHashMap<>();

    public static AppCompatDelegate get(Activity activity, AppCompatCallback callback) {
        AppCompatDelegate delegate = sDelegateMap.get(activity);
        if (delegate == null) {
            delegate = create(activity, activity.getWindow(), callback);
            sDelegateMap.put(activity, delegate);
        }
        return delegate;
    }

    private static AppCompatDelegate create(Context context, Window window, AppCompatCallback callback) {
        final int sdk = Build.VERSION.SDK_INT;
        if (BuildCompat.isAtLeastN()) {
            return new SkinAppCompatDelegateImplN(context, window, callback);
        } else if (sdk >= Build.VERSION_CODES.M) {
            return new SkinAppCompatDelegateImplV23(context, window, callback);
        } else if (sdk >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return new SkinAppCompatDelegateImplV14(context, window, callback);
        } else if (sdk >= Build.VERSION_CODES.HONEYCOMB) {
            return new SkinAppCompatDelegateImplV11(context, window, callback);
        } else {
            return new SkinAppCompatDelegateImplV9(context, window, callback);
        }
    }
}
