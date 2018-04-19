package skin.support;

import android.content.Context;

import skin.support.app.SkinRecyclerViewInflater;

public class SkinRecyclerViewManager {

    private static volatile SkinRecyclerViewManager sInstance;

    public static SkinRecyclerViewManager init(Context context) {
        if (sInstance == null) {
            synchronized (SkinRecyclerViewManager.class) {
                if (sInstance == null) {
                    sInstance = new SkinRecyclerViewManager(context);
                }
            }
        }
        return sInstance;
    }

    public static SkinRecyclerViewManager getInstance() {
        return sInstance;
    }

    private SkinRecyclerViewManager(Context context) {
        SkinCompatManager.init(context).addInflater(new SkinRecyclerViewInflater());
    }

}
