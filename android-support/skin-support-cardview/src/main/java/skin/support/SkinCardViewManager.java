package skin.support;

import android.content.Context;

import skin.support.app.SkinCardViewInflater;

/**
 * Created by ximsfei on 2017/3/5.
 */

public class SkinCardViewManager {
    private static volatile SkinCardViewManager sInstance;

    public static SkinCardViewManager init(Context context) {
        if (sInstance == null) {
            synchronized (SkinCardViewManager.class) {
                if (sInstance == null) {
                    sInstance = new SkinCardViewManager(context);
                }
            }
        }
        return sInstance;
    }

    public static SkinCardViewManager getInstance() {
        return sInstance;
    }

    private SkinCardViewManager(Context context) {
        SkinCompatManager.init(context).addInflater(new SkinCardViewInflater());
    }
}
