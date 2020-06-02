package skin.support.graphics;

/**
 * A set of color-related utility methods, building upon those available in {@code Color}.
 */
public final class ColorUtils {
    /**
     * Set the alpha component of {@code color} to be {@code alpha}.
     */
    public static int setAlphaComponent(int color, int alpha) {
        if (alpha < 0 || alpha > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (color & 0x00ffffff) | (alpha << 24);
    }
}

