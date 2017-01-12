package skin.support.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class SkinPreference {
    private static final String FILE_NAME = "meta-data";

    public static final String KEY_SKIN_PATH = "skin-path";
    private static SkinPreference sInstance;
    private final Context mApp;
    private final SharedPreferences mPref;
    private final SharedPreferences.Editor mEditor;

    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (SkinPreference.class) {
                if (sInstance == null) {
                    sInstance = new SkinPreference(context.getApplicationContext());
                }
            }
        }
    }

    public static SkinPreference getInstance() {
        return sInstance;
    }

    private SkinPreference(Context applicationContext) {
        mApp = applicationContext;
        mPref = mApp.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    public SkinPreference setSkinPath(String skinPath) {
        mEditor.putString(KEY_SKIN_PATH, skinPath);
        return this;
    }

    public String getSkinPath() {
        return mPref.getString(KEY_SKIN_PATH, "");
    }

    public void commitEditor() {
        mEditor.apply();
    }
}
