package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import skin.support.annotation.ColorRes;
import skin.support.exception.SkinCompatException;
import skin.support.utils.Slog;

public final class ColorState {
    private static final String TAG = "ColorState";
    boolean onlyDefaultColor;
    String colorName;
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
        this.onlyDefaultColor = TextUtils.isEmpty(colorWindowFocused)
                && TextUtils.isEmpty(colorSelected)
                && TextUtils.isEmpty(colorFocused)
                && TextUtils.isEmpty(colorEnabled)
                && TextUtils.isEmpty(colorPressed)
                && TextUtils.isEmpty(colorChecked)
                && TextUtils.isEmpty(colorActivated)
                && TextUtils.isEmpty(colorAccelerated)
                && TextUtils.isEmpty(colorHovered)
                && TextUtils.isEmpty(colorDragCanAccept)
                && TextUtils.isEmpty(colorDragHovered);
        if (onlyDefaultColor) {
            if (!colorDefault.startsWith("#")) {
                throw new SkinCompatException("Default color cannot be a reference, when only default color is available!");
            }
        }
    }

    ColorState(String colorName, String colorDefault) {
        this.colorName = colorName;
        this.colorDefault = colorDefault;
        this.onlyDefaultColor = true;
        if (!colorDefault.startsWith("#")) {
            throw new SkinCompatException("Default color cannot be a reference, when only default color is available!");
        }
    }

    public boolean isOnlyDefaultColor() {
        return onlyDefaultColor;
    }

    public String getColorName() {
        return colorName;
    }

    public String getColorWindowFocused() {
        return colorWindowFocused;
    }

    public String getColorSelected() {
        return colorSelected;
    }

    public String getColorFocused() {
        return colorFocused;
    }

    public String getColorEnabled() {
        return colorEnabled;
    }

    public String getColorPressed() {
        return colorPressed;
    }

    public String getColorChecked() {
        return colorChecked;
    }

    public String getColorActivated() {
        return colorActivated;
    }

    public String getColorAccelerated() {
        return colorAccelerated;
    }

    public String getColorHovered() {
        return colorHovered;
    }

    public String getColorDragCanAccept() {
        return colorDragCanAccept;
    }

    public String getColorDragHovered() {
        return colorDragHovered;
    }

    public String getColorDefault() {
        return colorDefault;
    }

    ColorStateList parse() {
        if (onlyDefaultColor) {
            int defaultColor = Color.parseColor(colorDefault);
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
                String windowFocusedColorStr = getColorStr(colorWindowFocused);
                if (!TextUtils.isEmpty(windowFocusedColorStr)) {
                    int windowFocusedColorInt = Color.parseColor(windowFocusedColorStr);
                    stateSetList.add(SkinCompatThemeUtils.WINDOW_FOCUSED_STATE_SET);
                    stateColorList.add(windowFocusedColorInt);
                    stateColorCount++;
                }
            } catch (Exception e) {
            }
        }
        if (!TextUtils.isEmpty(colorSelected)) {
            try {
                String colorSelectedStr = getColorStr(colorSelected);
                if (!TextUtils.isEmpty(colorSelectedStr)) {
                    int selectedColorInt = Color.parseColor(colorSelectedStr);
                    stateSetList.add(SkinCompatThemeUtils.SELECTED_STATE_SET);
                    stateColorList.add(selectedColorInt);
                    stateColorCount++;
                }
            } catch (Exception e) {
            }
        }
        if (!TextUtils.isEmpty(colorFocused)) {
            try {
                String colorFocusedStr = getColorStr(colorFocused);
                if (!TextUtils.isEmpty(colorFocusedStr)) {
                    int focusedColorInt = Color.parseColor(colorFocusedStr);
                    stateSetList.add(SkinCompatThemeUtils.FOCUSED_STATE_SET);
                    stateColorList.add(focusedColorInt);
                    stateColorCount++;
                }
            } catch (Exception e) {
            }
        }
        if (!TextUtils.isEmpty(colorEnabled)) {
            try {
                String colorEnabledStr = getColorStr(colorEnabled);
                if (!TextUtils.isEmpty(colorEnabledStr)) {
                    int enabledColorInt = Color.parseColor(colorEnabledStr);
                    stateSetList.add(SkinCompatThemeUtils.ENABLED_STATE_SET);
                    stateColorList.add(enabledColorInt);
                    stateColorCount++;
                }
            } catch (Exception e) {
            }
        }
        if (!TextUtils.isEmpty(colorPressed)) {
            try {
                String colorPressedStr = getColorStr(colorPressed);
                if (!TextUtils.isEmpty(colorPressedStr)) {
                    int pressedColorInt = Color.parseColor(colorPressedStr);
                    stateSetList.add(SkinCompatThemeUtils.PRESSED_STATE_SET);
                    stateColorList.add(pressedColorInt);
                    stateColorCount++;
                }
            } catch (Exception e) {
            }
        }
        if (!TextUtils.isEmpty(colorChecked)) {
            try {
                String colorCheckedStr = getColorStr(colorChecked);
                if (!TextUtils.isEmpty(colorCheckedStr)) {
                    int checkedColorInt = Color.parseColor(colorCheckedStr);
                    stateSetList.add(SkinCompatThemeUtils.CHECKED_STATE_SET);
                    stateColorList.add(checkedColorInt);
                    stateColorCount++;
                }
            } catch (Exception e) {
            }
        }
        if (!TextUtils.isEmpty(colorActivated)) {
            try {
                String colorActivatedStr = getColorStr(colorActivated);
                if (!TextUtils.isEmpty(colorActivatedStr)) {
                    int activatedColorInt = Color.parseColor(colorActivatedStr);
                    stateSetList.add(SkinCompatThemeUtils.ACTIVATED_STATE_SET);
                    stateColorList.add(activatedColorInt);
                    stateColorCount++;
                }
            } catch (Exception e) {
            }
        }
        if (!TextUtils.isEmpty(colorAccelerated)) {
            try {
                String colorAcceleratedStr = getColorStr(colorAccelerated);
                if (!TextUtils.isEmpty(colorAcceleratedStr)) {
                    int acceleratedColorInt = Color.parseColor(colorAcceleratedStr);
                    stateSetList.add(SkinCompatThemeUtils.ACCELERATED_STATE_SET);
                    stateColorList.add(acceleratedColorInt);
                    stateColorCount++;
                }
            } catch (Exception e) {
            }
        }
        if (!TextUtils.isEmpty(colorHovered)) {
            try {
                String colorHoveredStr = getColorStr(colorHovered);
                if (!TextUtils.isEmpty(colorHoveredStr)) {
                    int hoveredColorInt = Color.parseColor(colorHoveredStr);
                    stateSetList.add(SkinCompatThemeUtils.HOVERED_STATE_SET);
                    stateColorList.add(hoveredColorInt);
                    stateColorCount++;
                }
            } catch (Exception e) {
            }
        }
        if (!TextUtils.isEmpty(colorDragCanAccept)) {
            try {
                String colorDragCanAcceptStr = getColorStr(colorDragCanAccept);
                if (!TextUtils.isEmpty(colorDragCanAcceptStr)) {
                    int dragCanAcceptColorInt = Color.parseColor(colorDragCanAcceptStr);
                    stateSetList.add(SkinCompatThemeUtils.DRAG_CAN_ACCEPT_STATE_SET);
                    stateColorList.add(dragCanAcceptColorInt);
                    stateColorCount++;
                }
            } catch (Exception e) {
            }
        }
        if (!TextUtils.isEmpty(colorDragHovered)) {
            try {
                String colorDragHoveredStr = getColorStr(colorDragHovered);
                if (!TextUtils.isEmpty(colorDragHoveredStr)) {
                    int dragHoveredColorInt = Color.parseColor(colorDragHoveredStr);
                    stateSetList.add(SkinCompatThemeUtils.DRAG_HOVERED_STATE_SET);
                    stateColorList.add(dragHoveredColorInt);
                    stateColorCount++;
                }
            } catch (Exception e) {
            }
        }
        try {
            String colorDefaultStr = getColorStr(colorDefault);
            if (!TextUtils.isEmpty(colorDefaultStr)) {
                int baseColor = Color.parseColor(colorDefaultStr);
                stateSetList.add(SkinCompatThemeUtils.EMPTY_STATE_SET);
                stateColorList.add(baseColor);
                stateColorCount++;
            }

            final int[][] states = new int[stateColorCount][];
            final int[] colors = new int[stateColorCount];
            for (int index = 0; index < stateColorCount; index++) {
                states[index] = stateSetList.get(index);
                colors[index] = stateColorList.get(index);
            }
            return new ColorStateList(states, colors);
        } catch (Exception e) {
            if (Slog.DEBUG) {
                Slog.i(TAG, colorName + " parse failure.");
            }
            SkinCompatUserThemeManager.get().removeColorState(colorName);
            return null;
        }
    }

    private String getColorStr(String colorName) {
        if (colorName.startsWith("#")) {
            return colorName;
        } else {
            ColorState stateRef = SkinCompatUserThemeManager.get().getColorState(colorName);
            if (stateRef != null) {
                if (stateRef.isOnlyDefaultColor()) {
                    return stateRef.colorDefault;
                } else {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, colorName + " cannot reference " + stateRef.colorName);
                    }
                }
            }
        }
        return null;
    }

    static boolean checkColorValid(String name, String color) {
        // 不为空
        boolean colorValid = !TextUtils.isEmpty(color)
                // 不以#开始，说明是引用其他颜色值 或者以#开始，则长度必须为7或9
                && (!color.startsWith("#") || color.length() == 7 || color.length() == 9);
        if (Slog.DEBUG && !colorValid) {
            Slog.i(TAG, "Invalid color -> " + name + ": " + color);
        }
        return colorValid;
    }

    static JSONObject toJSONObject(ColorState state) throws JSONException {
        JSONObject object = new JSONObject();
        if (state.onlyDefaultColor) {
            object.putOpt("colorName", state.colorName)
                    .putOpt("colorDefault", state.colorDefault)
                    .putOpt("onlyDefaultColor", state.onlyDefaultColor);
        } else {
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
        }
        return object;
    }

    static ColorState fromJSONObject(JSONObject jsonObject) {
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
}
