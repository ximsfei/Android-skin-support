package skin.support;

import android.content.Context;

import skin.support.app.SkinHookAutoLayoutViewInflater;

/**
 * Created by pengfengwang on 2017/3/15.
 */

public class SkinHookAutoLayoutManager {
    private static volatile SkinHookAutoLayoutManager sInstance;

    public static SkinHookAutoLayoutManager init(Context context) {
        if (sInstance == null) {
            synchronized (SkinHookAutoLayoutManager.class) {
                if (sInstance == null) {
                    sInstance = new SkinHookAutoLayoutManager(context);
                }
            }
        }
        return sInstance;
    }

    public static SkinHookAutoLayoutManager getInstance() {
        return sInstance;
    }

    private SkinHookAutoLayoutManager(Context context) {
        SkinCompatManager.init(context).addHookInflater(new SkinHookAutoLayoutViewInflater());
    }
}
