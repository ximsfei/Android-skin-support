package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.ColorRes;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

import skin.support.SkinCompatManager;
import skin.support.exception.SkinCompatException;
import skin.support.utils.SkinPreference;
import skin.support.utils.Slog;

public class SkinCompatUserColorManager {
    private static final String TAG = "SkinCompatColorManager";
    private static SkinCompatUserColorManager INSTANCE = new SkinCompatUserColorManager();
    private final HashMap<String, ColorState> mColorNameStateMap = new HashMap<>();
    private final Object mColorCacheLock = new Object();
    private final WeakHashMap<Integer, WeakReference<ColorStateList>> mColorCaches = new WeakHashMap<>();
    private boolean mEmpty;

    private SkinCompatUserColorManager() {
        loadColorNameStateMapFromSharedPreferences();
    }

    private void loadColorNameStateMapFromSharedPreferences() {
        String colors = SkinPreference.getInstance().getColors();
        if (!TextUtils.isEmpty(colors)) {
            try {
                JSONArray jsonArray = new JSONArray(colors);
                if (Slog.DEBUG) {
                    Slog.i(TAG, "Load from SharedPreferences: " + jsonArray.toString());
                }
                int count = jsonArray.length();
                for (int i = 0; i < count; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    ColorState state = fromJSONObject(jsonObject);
                    if (state != null) {
                        mColorNameStateMap.put(state.colorName, state);
                    }
                }
                mEmpty = mColorNameStateMap.isEmpty();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public static SkinCompatUserColorManager get() {
        return INSTANCE;
    }

    public void addColorState(@ColorRes int colorRes, ColorState state) {
        String entry = getColorEntryName(colorRes);
        if (!TextUtils.isEmpty(entry) && state != null) {
            state.colorName = entry;
            mColorNameStateMap.put(entry, state);
            removeColorInCache(colorRes);
            mEmpty = false;
        }
    }

    public void addColorState(@ColorRes int colorRes, String colorDefault) {
        if (!checkColorValid("colorDefault", colorDefault)) {
            return;
        }
        String entry = getColorEntryName(colorRes);
        if (!TextUtils.isEmpty(entry)) {
            mColorNameStateMap.put(entry, new ColorState(entry, colorDefault));
            removeColorInCache(colorRes);
            mEmpty = false;
        }
    }

    public void removeColorState(@ColorRes int colorRes) {
        String entry = getColorEntryName(colorRes);
        if (!TextUtils.isEmpty(entry)) {
            mColorNameStateMap.remove(entry);
            removeColorInCache(colorRes);
            mEmpty = mColorNameStateMap.isEmpty();
        }
    }

    public void removeColorState(String colorName) {
        if (!TextUtils.isEmpty(colorName)) {
            mColorNameStateMap.remove(colorName);
            mEmpty = mColorNameStateMap.isEmpty();
        }
    }

    public ColorState getColorState(String colorName) {
        return mColorNameStateMap.get(colorName);
    }

    public ColorState getColorState(@ColorRes int colorRes) {
        String entry = getColorEntryName(colorRes);
        if (!TextUtils.isEmpty(entry)) {
            return mColorNameStateMap.get(entry);
        }
        return null;
    }

    public ColorStateList getColorStateList(@ColorRes int colorRes) {
        ColorStateList colorStateList = getCachedColor(colorRes);
        if (colorStateList == null) {
            String entry = getColorEntryName(colorRes);
            if (!TextUtils.isEmpty(entry)) {
                ColorState state = mColorNameStateMap.get(entry);
                if (state != null) {
                    colorStateList = state.parse();
                    if (colorStateList != null) {
                        addColorToCache(colorRes, colorStateList);
                    }
                }
            }
        }
        return colorStateList;
    }

    public boolean isEmpty() {
        return mEmpty;
    }

    public void clearColors() {
        mColorNameStateMap.clear();
        clearCaches();
        mEmpty = true;
        SkinPreference.getInstance().setColors("").commitEditor();
        SkinCompatManager.getInstance().notifyUpdateSkin();
    }

    void clearCaches() {
        synchronized (mColorCacheLock) {
            mColorCaches.clear();
        }
    }

    public void applyColors() {
        JSONArray jsonArray = new JSONArray();
        for (String colorName : mColorNameStateMap.keySet()) {
            ColorState state = mColorNameStateMap.get(colorName);
            if (state != null) {
                jsonArray.put(toJSONObject(state));
            }
        }
        if (Slog.DEBUG) {
            Slog.i(TAG, "Apply custom colors: " + jsonArray.toString());
        }
        SkinPreference.getInstance().setColors(jsonArray.toString()).commitEditor();
        SkinCompatManager.getInstance().notifyUpdateSkin();
    }

    private String getColorEntryName(@ColorRes int colorRes) {
        Context context = SkinCompatManager.getInstance().getContext();
        String type = context.getResources().getResourceTypeName(colorRes);
        if ("color".equalsIgnoreCase(type)) {
            return context.getResources().getResourceEntryName(colorRes);
        }
        return null;
    }

    private ColorStateList getCachedColor(@ColorRes int colorRes) {
        synchronized (mColorCacheLock) {
            WeakReference<ColorStateList> colorRef = mColorCaches.get(colorRes);
            if (colorRef != null) {
                ColorStateList colorStateList = colorRef.get();
                if (colorStateList != null) {
                    return colorStateList;
                } else {
                    mColorCaches.remove(colorRes);
                }
            }
        }
        return null;
    }

    private void addColorToCache(@ColorRes int colorRes, ColorStateList colorStateList) {
        if (colorStateList != null) {
            synchronized (mColorCacheLock) {
                mColorCaches.put(colorRes, new WeakReference<>(colorStateList));
            }
        }
    }

    private void removeColorInCache(@ColorRes int colorRes) {
        synchronized (mColorCacheLock) {
            mColorCaches.remove(colorRes);
        }
    }

    private JSONObject toJSONObject(ColorState state) {
        JSONObject object = new JSONObject();
        if (state.onlyDefaultColor) {
            try {
                object.putOpt("colorName", state.colorName)
                        .putOpt("colorDefault", state.colorDefault)
                        .putOpt("onlyDefaultColor", state.onlyDefaultColor);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                object.putOpt("colorName", state.colorName)
                        .putOpt("colorWindowFocused", state.colorWindowFocused)
                        .putOpt("colorSelected", state.colorSelected)
                        .putOpt("colorFocused", state.colorFocused)
                        .putOpt("colorEnabled", state.colorEnabled)
                        .putOpt("colorPressed", state.colorPressed)
                        .putOpt("colorChecked", state.colorChecked)
                        .putOpt("colorActivated", state.colorActivated)
                        .putOpt("colorAccelerated", state.colorAccelerated)
                        .putOpt("colorHovered", state.colorHovered)
                        .putOpt("colorDragCanAccept", state.colorDragCanAccept)
                        .putOpt("colorDragHovered", state.colorDragHovered)
                        .putOpt("colorDefault", state.colorDefault)
                        .putOpt("onlyDefaultColor", state.onlyDefaultColor);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    private ColorState fromJSONObject(JSONObject jsonObject) {
        if (jsonObject.has("colorName")
                && jsonObject.has("colorDefault")
                && jsonObject.has("onlyDefaultColor")) {
            try {
                boolean onlyDefaultColor = jsonObject.getBoolean("onlyDefaultColor");
                String colorName = jsonObject.getString("colorName");
                String colorDefault = jsonObject.getString("colorDefault");
                if (onlyDefaultColor) {
                    return new ColorState(colorName, colorDefault);
                } else {
                    ColorBuilder builder = new ColorBuilder();
                    builder.setColorDefault(colorDefault);
                    if (jsonObject.has("colorWindowFocused")) {
                        builder.setColorWindowFocused(jsonObject.getString("colorWindowFocused"));
                    }
                    if (jsonObject.has("colorSelected")) {
                        builder.setColorSelected(jsonObject.getString("colorSelected"));
                    }
                    if (jsonObject.has("colorFocused")) {
                        builder.setColorFocused(jsonObject.getString("colorFocused"));
                    }
                    if (jsonObject.has("colorEnabled")) {
                        builder.setColorEnabled(jsonObject.getString("colorEnabled"));
                    }
                    if (jsonObject.has("colorPressed")) {
                        builder.setColorPressed(jsonObject.getString("colorPressed"));
                    }
                    if (jsonObject.has("colorChecked")) {
                        builder.setColorChecked(jsonObject.getString("colorChecked"));
                    }
                    if (jsonObject.has("colorActivated")) {
                        builder.setColorActivated(jsonObject.getString("colorActivated"));
                    }
                    if (jsonObject.has("colorAccelerated")) {
                        builder.setColorAccelerated(jsonObject.getString("colorAccelerated"));
                    }
                    if (jsonObject.has("colorHovered")) {
                        builder.setColorHovered(jsonObject.getString("colorHovered"));
                    }
                    if (jsonObject.has("colorDragCanAccept")) {
                        builder.setColorDragCanAccept(jsonObject.getString("colorDragCanAccept"));
                    }
                    if (jsonObject.has("colorDragHovered")) {
                        builder.setColorDragHovered(jsonObject.getString("colorDragHovered"));
                    }
                    ColorState state = builder.build();
                    state.colorName = colorName;
                    return state;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static class ColorBuilder {
        String colorWindowFocused;
        String colorSelected;
        String colorFocused;
        String colorEnabled;
        String colorPressed;
        String colorChecked;
        String colorActivated;
        String colorAccelerated;
        String colorHovered;
        String colorDragCanAccept;
        String colorDragHovered;
        String colorDefault;

        public ColorBuilder() {
        }

        public ColorBuilder(ColorState state) {
            colorWindowFocused = state.colorWindowFocused;
            colorSelected = state.colorSelected;
            colorFocused = state.colorFocused;
            colorEnabled = state.colorEnabled;
            colorPressed = state.colorPressed;
            colorChecked = state.colorChecked;
            colorActivated = state.colorActivated;
            colorAccelerated = state.colorAccelerated;
            colorHovered = state.colorHovered;
            colorDragCanAccept = state.colorDragCanAccept;
            colorDragHovered = state.colorDragHovered;
            colorDefault = state.colorDefault;
        }

        public ColorBuilder setColorWindowFocused(String colorWindowFocused) {
            if (checkColorValid("colorWindowFocused", colorWindowFocused)) {
                this.colorWindowFocused = colorWindowFocused;
            }
            return this;

        }

        public ColorBuilder setColorWindowFocused(Context context, @ColorRes int colorRes) {
            this.colorWindowFocused = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorSelected(String colorSelected) {
            if (checkColorValid("colorSelected", colorSelected)) {
                this.colorSelected = colorSelected;
            }
            return this;
        }

        public ColorBuilder setColorSelected(Context context, @ColorRes int colorRes) {
            this.colorSelected = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorFocused(String colorFocused) {
            if (checkColorValid("colorFocused", colorFocused)) {
                this.colorFocused = colorFocused;
            }
            return this;
        }

        public ColorBuilder setColorFocused(Context context, @ColorRes int colorRes) {
            this.colorFocused = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorEnabled(String colorEnabled) {
            if (checkColorValid("colorEnabled", colorEnabled)) {
                this.colorEnabled = colorEnabled;
            }
            return this;
        }

        public ColorBuilder setColorEnabled(Context context, @ColorRes int colorRes) {
            this.colorEnabled = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorChecked(String colorChecked) {
            if (checkColorValid("colorChecked", colorChecked)) {
                this.colorChecked = colorChecked;
            }
            return this;
        }

        public ColorBuilder setColorChecked(Context context, @ColorRes int colorRes) {
            this.colorChecked = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorPressed(String colorPressed) {
            if (checkColorValid("colorPressed", colorPressed)) {
                this.colorPressed = colorPressed;
            }
            return this;
        }

        public ColorBuilder setColorPressed(Context context, @ColorRes int colorRes) {
            this.colorPressed = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorActivated(String colorActivated) {
            if (checkColorValid("colorActivated", colorActivated)) {
                this.colorActivated = colorActivated;
            }
            return this;
        }

        public ColorBuilder setColorActivated(Context context, @ColorRes int colorRes) {
            this.colorActivated = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorAccelerated(String colorAccelerated) {
            if (checkColorValid("colorAccelerated", colorAccelerated)) {
                this.colorAccelerated = colorAccelerated;
            }
            return this;
        }

        public ColorBuilder setColorAccelerated(Context context, @ColorRes int colorRes) {
            this.colorAccelerated = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorHovered(String colorHovered) {
            if (checkColorValid("colorHovered", colorHovered)) {
                this.colorHovered = colorHovered;
            }
            return this;
        }

        public ColorBuilder setColorHovered(Context context, @ColorRes int colorRes) {
            this.colorHovered = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorDragCanAccept(String colorDragCanAccept) {
            if (checkColorValid("colorDragCanAccept", colorDragCanAccept)) {
                this.colorDragCanAccept = colorDragCanAccept;
            }
            return this;
        }

        public ColorBuilder setColorDragCanAccept(Context context, @ColorRes int colorRes) {
            this.colorDragCanAccept = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorDragHovered(String colorDragHovered) {
            if (checkColorValid("colorDragHovered", colorDragHovered)) {
                this.colorDragHovered = colorDragHovered;
            }
            return this;
        }

        public ColorBuilder setColorDragHovered(Context context, @ColorRes int colorRes) {
            this.colorDragHovered = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorDefault(String colorDefault) {
            if (checkColorValid("colorDefault", colorDefault)) {
                this.colorDefault = colorDefault;
            }
            return this;
        }

        public ColorBuilder setColorDefault(Context context, @ColorRes int colorRes) {
            this.colorDefault = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorState build() {
            if (TextUtils.isEmpty(colorDefault)) {
                throw new SkinCompatException("Default color can not empty!");
            }
            return new ColorState(colorWindowFocused, colorSelected, colorFocused,
                    colorEnabled, colorPressed, colorChecked, colorActivated, colorAccelerated,
                    colorHovered, colorDragCanAccept, colorDragHovered, colorDefault);
        }
    }

    private static boolean checkColorValid(String name, String color) {
        // 不为空
        boolean colorValid = !TextUtils.isEmpty(color)
                // 不以#开始，说明是引用其他颜色值 或者以#开始，则长度必须为7或9
                && (!color.startsWith("#") || color.length() == 7 || color.length() == 9);
        if (Slog.DEBUG && !colorValid) {
            Slog.i(TAG, "Invalid color -> " + name + ": " + color);
        }
        return colorValid;
    }
}
