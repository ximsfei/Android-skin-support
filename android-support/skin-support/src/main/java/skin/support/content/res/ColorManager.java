package skin.support.content.res;

import android.content.res.ColorStateList;

import java.util.HashMap;

public class ColorManager {
    private static ColorManager INSTANCE = new ColorManager();
    private HashMap<String, ColorState> mResNameStateMap = new HashMap<>();

    private ColorManager() {
        // TODO load from SharedPreferences.
    }

    public static ColorManager getInstance() {
        return INSTANCE;
    }

    public void addColorState(String colorName, ColorState state) {
        mResNameStateMap.put(colorName, state);
        // TODO write SharedPreferences.
    }

    public ColorStateList getColorStateList(int resId) {
//        ColorState state = mResNameStateMap.get(colorName);
//        if (state != null) {
            // TODO parse ColorStateList.
//        }
        return null;
    }

    public boolean hasColorState(String colorName) {
        return mResNameStateMap.containsKey(colorName);
    }

    public void clear() {
        mResNameStateMap.clear();
        // TODO clear SharedPreferences.
    }

    public void clearCaches() {
        // TODO clear caches.
    }

    public static class ColorState {
        String colorWindowFocused;
        String colorSelected;
        String colorFocused;
        String colorEnabled;
        String colorPressed;
        String colorActivated;
        String colorAccelerated;
        String colorHovered;
        String colorDragCanAccept;
        String colorDragHovered;
        String colorDefault;

        ColorState(String colorWindowFocused, String colorSelected, String colorFocused,
                   String colorEnabled, String colorPressed, String colorActivated,
                   String colorAccelerated, String colorHovered, String colorDragCanAccept,
                   String colorDragHovered, String colorDefault) {
            this.colorWindowFocused = colorWindowFocused;
            this.colorSelected = colorSelected;
            this.colorFocused = colorFocused;
            this.colorEnabled = colorEnabled;
            this.colorPressed = colorPressed;
            this.colorActivated = colorActivated;
            this.colorAccelerated = colorAccelerated;
            this.colorHovered = colorHovered;
            this.colorDragCanAccept = colorDragCanAccept;
            this.colorDragHovered = colorDragHovered;
            this.colorDefault = colorDefault;
        }

        public ColorState(String colorDefault) {
            this.colorDefault = colorDefault;
        }
    }

    public static class ColorBuilder {
        String colorWindowFocused;
        String colorSelected;
        String colorFocused;
        String colorEnabled;
        String colorPressed;
        String colorActivated;
        String colorAccelerated;
        String colorHovered;
        String colorDragCanAccept;
        String colorDragHovered;
        String colorDefault;

        public ColorBuilder setColorWindowFocused(String colorWindowFocused) {
            this.colorWindowFocused = colorWindowFocused;
            return this;
        }

        public ColorBuilder setColorSelected(String colorSelected) {
            this.colorSelected = colorSelected;
            return this;
        }

        public ColorBuilder setColorFocused(String colorFocused) {
            this.colorFocused = colorFocused;
            return this;
        }

        public ColorBuilder setColorEnabled(String colorEnabled) {
            this.colorEnabled = colorEnabled;
            return this;
        }

        public ColorBuilder setColorPressed(String colorPressed) {
            this.colorPressed = colorPressed;
            return this;
        }

        public ColorBuilder setColorActivated(String colorActivated) {
            this.colorActivated = colorActivated;
            return this;
        }

        public ColorBuilder setColorAccelerated(String colorAccelerated) {
            this.colorAccelerated = colorAccelerated;
            return this;
        }

        public ColorBuilder setColorHovered(String colorHovered) {
            this.colorHovered = colorHovered;
            return this;
        }

        public ColorBuilder setColorDragCanAccept(String colorDragCanAccept) {
            this.colorDragCanAccept = colorDragCanAccept;
            return this;
        }

        public ColorBuilder setColorDragHovered(String colorDragHovered) {
            this.colorDragHovered = colorDragHovered;
            return this;
        }

        public ColorBuilder setColorDefault(String colorDefault) {
            this.colorDefault = colorDefault;
            return this;
        }

        public ColorState build() {
            return new ColorState(colorWindowFocused, colorSelected, colorFocused,
                    colorEnabled, colorPressed, colorActivated, colorAccelerated,
                    colorHovered, colorDragCanAccept, colorDragHovered, colorDefault);
        }
    }
}
