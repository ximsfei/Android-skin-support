package android.support.v7.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Window;

@RequiresApi(api = Build.VERSION_CODES.M)
@TargetApi(Build.VERSION_CODES.M)
class SkinAppCompatDelegateImplV23 extends AppCompatDelegateImplV23 {

    SkinAppCompatDelegateImplV23(Context context, Window window, AppCompatCallback callback) {
        super(context, window, callback);
    }

    @Override
    public void installViewFactory() {
    }
}
