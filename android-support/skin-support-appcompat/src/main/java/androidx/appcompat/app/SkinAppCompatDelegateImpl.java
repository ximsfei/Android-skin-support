package androidx.appcompat.app;

import android.app.Activity;
import android.content.Context;
import android.view.Window;

import java.util.Map;
import java.util.WeakHashMap;

public class SkinAppCompatDelegateImpl extends AppCompatDelegateImpl {
    private static Map<Activity, AppCompatDelegate> sDelegateMap = new WeakHashMap<>();

    public static AppCompatDelegate get(Activity activity, AppCompatCallback callback) {
        AppCompatDelegate delegate = sDelegateMap.get(activity);
        if (delegate == null) {
            delegate = new SkinAppCompatDelegateImpl(activity, activity.getWindow(), callback);
            sDelegateMap.put(activity, delegate);
        }
        return delegate;
    }

    private SkinAppCompatDelegateImpl(Context context, Window window, AppCompatCallback callback) {
        super(context, window, callback);
    }

    @Override
    public void installViewFactory() {
    }
}
