package skin.support.utils;

import android.content.Context;
import android.content.SharedPreferences;

import skin.support.SkinCompatManager;

public class SkinPreference {
    private static final String FILE_NAME = "meta-data";

    private static final String KEY_SKIN_NAME = "skin-name";
    private static final String KEY_SKIN_STRATEGY = "skin-strategy";
    private static volatile SkinPreference sInstance;
    private final Context mApp;
    private final SharedPreferences mPref;
    private final SharedPreferences.Editor mEditor;

    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (SkinPreference.class) {
                sInstance = new SkinPreference(context.getApplicationContext());
            }
        }
    }

    public static SkinPreference getInstance() {
        return sInstance;
    }

    private SkinPreference(Context applicationContext) {
        mApp = applicationContext;
        mPref = mApp.getSharedPreferences(mApp.getPackageName() + FILE_NAME, Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    public SkinPreference setSkinName(String skinName) {
        mEditor.putString(KEY_SKIN_NAME, skinName);
        return this;
    }

    public String getSkinName() {
        return mPref.getString(KEY_SKIN_NAME, "");
    }

    public SkinPreference setSkinStrategy(int strategy) {
        mEditor.putInt(KEY_SKIN_STRATEGY, strategy);
        return this;
    }

    public int getSkinStrategy() {
        return mPref.getInt(KEY_SKIN_STRATEGY, SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS);
    }

    public void commitEditor() {
        mEditor.apply();
    }
}
