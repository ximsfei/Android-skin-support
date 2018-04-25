package skin.support.content.res;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

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
            SkinCompatUserColorManager.get().removeColorState(colorName);
            return null;
        }
    }

    private String getColorStr(String colorName) {
        if (colorName.startsWith("#")) {
            return colorName;
        } else {
            ColorState stateRef = SkinCompatUserColorManager.get().getColorState(colorName);
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
}
