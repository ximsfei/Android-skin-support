package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;

import skin.support.SkinCompatManager;
import skin.support.exception.SkinCompatException;
import skin.support.utils.SkinPreference;
import skin.support.utils.Slog;

public class SkinCompatColorManager {
    private static final String TAG = "SkinCompatColorManager";
    private static SkinCompatColorManager INSTANCE = new SkinCompatColorManager();
    private final HashMap<String, ColorState> mColorNameStateMap = new HashMap<>();
    private final Object mColorCacheLock = new Object();
    private final WeakHashMap<Integer, WeakReference<ColorStateList>> mColorCaches = new WeakHashMap<>();
    private boolean mEmpty;

    private SkinCompatColorManager() {
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

    public static SkinCompatColorManager get() {
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

    public ColorStateList getColorStateList(@ColorRes int colorRes) {
        ColorStateList colorStateList = getCachedColor(colorRes);
        if (colorStateList == null) {
            String entry = getColorEntryName(colorRes);
            if (!TextUtils.isEmpty(entry)) {
                ColorState state = mColorNameStateMap.get(entry);
                if (state != null) {
                    colorStateList = state.parse();
                    addColorToCache(colorRes, colorStateList);
                }
            }
        }
        return colorStateList;
    }

    public boolean hasColorState(String colorName) {
        return mColorNameStateMap.containsKey(colorName);
    }

    public boolean hasColorState(@ColorRes int colorRes) {
        String entry = getColorEntryName(colorRes);
        if (!TextUtils.isEmpty(entry)) {
            return mColorNameStateMap.containsKey(entry);
        }
        return false;
    }

    public boolean isEmpty() {
        return mEmpty;
    }

    public void clearColors() {
        mColorNameStateMap.clear();
        clearCaches();
        mEmpty = true;
        SkinPreference.getInstance().setColors("").commitEditor();
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

    static class ColorState {
        private boolean onlyDefaultColor;
        private String colorName;
        private String colorWindowFocused;
        private String colorSelected;
        private String colorFocused;
        private String colorEnabled;
        private String colorPressed;
        private String colorChecked;
        private String colorActivated;
        private String colorAccelerated;
        private String colorHovered;
        private String colorDragCanAccept;
        private String colorDragHovered;
        private String colorDefault;

        ColorState(String colorWindowFocused, String colorSelected, String colorFocused,
                   String colorEnabled, String colorPressed, String colorChecked, String colorActivated,
                   String colorAccelerated, String colorHovered, String colorDragCanAccept,
                   String colorDragHovered, String colorDefault) {
            this.colorWindowFocused = colorWindowFocused;
            this.colorSelected = colorSelected;
            this.colorFocused = colorFocused;
            this.colorEnabled = colorEnabled;
            this.colorPressed = colorPressed;
            this.colorChecked = colorChecked;
            this.colorActivated = colorActivated;
            this.colorAccelerated = colorAccelerated;
            this.colorHovered = colorHovered;
            this.colorDragCanAccept = colorDragCanAccept;
            this.colorDragHovered = colorDragHovered;
            this.colorDefault = colorDefault;
            this.onlyDefaultColor = false;
        }

        ColorState(String colorName, String colorDefault) {
            this.colorName = colorName;
            this.colorDefault = colorDefault;
            this.onlyDefaultColor = true;
        }

        ColorStateList parse() {
            if (onlyDefaultColor) {
                int defaultColor = Color.parseColor("#" + colorDefault);
                return ColorStateList.valueOf(defaultColor);
            }
            return parseAll();
        }

        private ColorStateList parseAll() {
            int stateColorCount = 0;
            List<int[]> stateSetList = new ArrayList<>();
            List<Integer> stateColorList = new ArrayList<>();
            if (!TextUtils.isEmpty(colorWindowFocused)) {
                try {
                    int windowFocusedColorInt = Color.parseColor("#" + colorWindowFocused);
                    stateSetList.add(SkinCompatThemeUtils.WINDOW_FOCUSED_STATE_SET);
                    stateColorList.add(windowFocusedColorInt);
                    stateColorCount++;
                } catch (Exception e) {
                }
            }
            if (!TextUtils.isEmpty(colorSelected)) {
                try {
                    int selectedColorInt = Color.parseColor("#" + colorSelected);
                    stateSetList.add(SkinCompatThemeUtils.SELECTED_STATE_SET);
                    stateColorList.add(selectedColorInt);
                    stateColorCount++;
                } catch (Exception e) {
                }
            }
            if (!TextUtils.isEmpty(colorFocused)) {
                try {
                    int focusedColorInt = Color.parseColor("#" + colorFocused);
                    stateSetList.add(SkinCompatThemeUtils.FOCUSED_STATE_SET);
                    stateColorList.add(focusedColorInt);
                    stateColorCount++;
                } catch (Exception e) {
                }
            }
            if (!TextUtils.isEmpty(colorEnabled)) {
                try {
                    int enabledColorInt = Color.parseColor("#" + colorEnabled);
                    stateSetList.add(SkinCompatThemeUtils.ENABLED_STATE_SET);
                    stateColorList.add(enabledColorInt);
                    stateColorCount++;
                } catch (Exception e) {
                }
            }
            if (!TextUtils.isEmpty(colorPressed)) {
                try {
                    int pressedColorInt = Color.parseColor("#" + colorPressed);
                    stateSetList.add(SkinCompatThemeUtils.PRESSED_STATE_SET);
                    stateColorList.add(pressedColorInt);
                    stateColorCount++;
                } catch (Exception e) {
                }
            }
            if (!TextUtils.isEmpty(colorChecked)) {
                try {
                    int checkeddColorInt = Color.parseColor("#" + colorChecked);
                    stateSetList.add(SkinCompatThemeUtils.CHECKED_STATE_SET);
                    stateColorList.add(checkeddColorInt);
                    stateColorCount++;
                } catch (Exception e) {
                }
            }
            if (!TextUtils.isEmpty(colorActivated)) {
                try {
                    int activatedColorInt = Color.parseColor("#" + colorActivated);
                    stateSetList.add(SkinCompatThemeUtils.ACTIVATED_STATE_SET);
                    stateColorList.add(activatedColorInt);
                    stateColorCount++;
                } catch (Exception e) {
                }
            }
            if (!TextUtils.isEmpty(colorAccelerated)) {
                try {
                    int acceleratedColorInt = Color.parseColor("#" + colorAccelerated);
                    stateSetList.add(SkinCompatThemeUtils.ACCELERATED_STATE_SET);
                    stateColorList.add(acceleratedColorInt);
                    stateColorCount++;
                } catch (Exception e) {
                }
            }
            if (!TextUtils.isEmpty(colorHovered)) {
                try {
                    int hoveredColorInt = Color.parseColor("#" + colorHovered);
                    stateSetList.add(SkinCompatThemeUtils.HOVERED_STATE_SET);
                    stateColorList.add(hoveredColorInt);
                    stateColorCount++;
                } catch (Exception e) {
                }
            }
            if (!TextUtils.isEmpty(colorDragCanAccept)) {
                try {
                    int dragCanAcceptColorInt = Color.parseColor("#" + colorDragCanAccept);
                    stateSetList.add(SkinCompatThemeUtils.DRAG_CAN_ACCEPT_STATE_SET);
                    stateColorList.add(dragCanAcceptColorInt);
                    stateColorCount++;
                } catch (Exception e) {
                }
            }
            if (!TextUtils.isEmpty(colorDragHovered)) {
                try {
                    int dragHoveredColorInt = Color.parseColor("#" + colorDragHovered);
                    stateSetList.add(SkinCompatThemeUtils.DRAG_HOVERED_STATE_SET);
                    stateColorList.add(dragHoveredColorInt);
                    stateColorCount++;
                } catch (Exception e) {
                }
            }
            int baseColor = Color.parseColor("#" + colorDefault);
            stateSetList.add(SkinCompatThemeUtils.EMPTY_STATE_SET);
            stateColorList.add(baseColor);
            stateColorCount++;

            final int[][] states = new int[stateColorCount][];
            final int[] colors = new int[stateColorCount];
            for (int index = 0; index < stateColorCount; index++) {
                states[index] = stateSetList.get(index);
                colors[index] = stateColorList.get(index);
            }
            return new ColorStateList(states, colors);
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

        public ColorBuilder setColorWindowFocused(String colorWindowFocused) {
            if (checkColorValid("colorWindowFocused", colorWindowFocused)) {
                this.colorWindowFocused = colorWindowFocused;
            }
            return this;
        }

        public ColorBuilder setColorSelected(String colorSelected) {
            if (checkColorValid("colorSelected", colorSelected)) {
                this.colorSelected = colorSelected;
            }
            return this;
        }

        public ColorBuilder setColorFocused(String colorFocused) {
            if (checkColorValid("colorFocused", colorFocused)) {
                this.colorFocused = colorFocused;
            }
            return this;
        }

        public ColorBuilder setColorEnabled(String colorEnabled) {
            if (checkColorValid("colorEnabled", colorEnabled)) {
                this.colorEnabled = colorEnabled;
            }
            return this;
        }

        public ColorBuilder setColorChecked(String colorChecked) {
            if (checkColorValid("colorChecked", colorChecked)) {
                this.colorChecked = colorChecked;
            }
            return this;
        }

        public ColorBuilder setColorPressed(String colorPressed) {
            if (checkColorValid("colorPressed", colorPressed)) {
                this.colorPressed = colorPressed;
            }
            return this;
        }

        public ColorBuilder setColorActivated(String colorActivated) {
            if (checkColorValid("colorActivated", colorActivated)) {
                this.colorActivated = colorActivated;
            }
            return this;
        }

        public ColorBuilder setColorAccelerated(String colorAccelerated) {
            if (checkColorValid("colorAccelerated", colorAccelerated)) {
                this.colorAccelerated = colorAccelerated;
            }
            return this;
        }

        public ColorBuilder setColorHovered(String colorHovered) {
            if (checkColorValid("colorHovered", colorHovered)) {
                this.colorHovered = colorHovered;
            }
            return this;
        }

        public ColorBuilder setColorDragCanAccept(String colorDragCanAccept) {
            if (checkColorValid("colorDragCanAccept", colorDragCanAccept)) {
                this.colorDragCanAccept = colorDragCanAccept;
            }
            return this;
        }

        public ColorBuilder setColorDragHovered(String colorDragHovered) {
            if (checkColorValid("colorDragHovered", colorDragHovered)) {
                this.colorDragHovered = colorDragHovered;
            }
            return this;
        }

        public ColorBuilder setColorDefault(String colorDefault) {
            if (checkColorValid("colorDefault", colorDefault)) {
                this.colorDefault = colorDefault;
            }
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
        boolean colorValid = !TextUtils.isEmpty(color) && (color.length() == 6 || color.length() == 8);
        if (Slog.DEBUG && !colorValid) {
            Slog.i(TAG, "Invalid color -> " + name + ": " + color);
        }
        return colorValid;
    }
}
