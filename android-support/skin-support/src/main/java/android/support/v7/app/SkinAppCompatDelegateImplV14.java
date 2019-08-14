package android.support.v7.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Window;

@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
class SkinAppCompatDelegateImplV14 extends AppCompatDelegateImplV14 {

    SkinAppCompatDelegateImplV14(Context context, Window window, AppCompatCallback callback) {
        super(context, window, callback);
    }

    @Override
    public void installViewFactory() {
    }
}
