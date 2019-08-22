package skin.support.constraint;

import android.content.Context;

import skin.support.SkinCompatManager;
import skin.support.constraint.app.SkinConstraintViewInflater;

/**
 * Created by ximsfei on 2017/1/13.
 */

public class SkinConstraintManager {
    private static volatile SkinConstraintManager sInstance;

    public static SkinConstraintManager init(Context context) {
        if (sInstance == null) {
            synchronized (SkinConstraintManager.class) {
                if (sInstance == null) {
                    sInstance = new SkinConstraintManager(context);
                }
            }
        }
        return sInstance;
    }

    public static SkinConstraintManager getInstance() {
        return sInstance;
    }

    private SkinConstraintManager(Context context) {
        SkinCompatManager.init(context).addInflater(new SkinConstraintViewInflater());
    }
}
