package skin.support.utils;

import android.graphics.drawable.Drawable;

import java.lang.reflect.Method;

public final class SkinCompatVersionUtils {
    private static final String TAG = "SkinCompatUtils";
    // 27.1.0后删除
    private static Class<?> sV4DrawableWrapperClass;
    private static Method sV4DrawableWrapperGetM;
    private static Method sV4DrawableWrapperSetM;
    // 27.1.0后增加
    private static Class<?> sV4WrappedDrawableClass;
    private static Method sV4WrappedDrawableGetM;
    private static Method sV4WrappedDrawableSetM;

    static {
        try {
            sV4WrappedDrawableClass = Class.forName("android.support.v4.graphics.drawable.WrappedDrawable");
        } catch (ClassNotFoundException e) {
            if (Slog.DEBUG) {
                Slog.i(TAG, "hasWrappedDrawable = false");
            }
        }
        try {
            sV4DrawableWrapperClass = Class.forName("android.support.v4.graphics.drawable.DrawableWrapper");
        } catch (ClassNotFoundException e) {
            if (Slog.DEBUG) {
                Slog.i(TAG, "hasDrawableWrapper = false");
            }
        }
    }

    public static boolean hasV4WrappedDrawable() {
        return sV4WrappedDrawableClass != null;
    }

    public static boolean isV4WrappedDrawable(Drawable drawable) {
        return sV4WrappedDrawableClass != null
                && sV4WrappedDrawableClass.isAssignableFrom(drawable.getClass());
    }

    public static Drawable getV4WrappedDrawableWrappedDrawable(Drawable drawable) {
        if (sV4WrappedDrawableClass != null) {
            if (sV4WrappedDrawableGetM == null) {
                try {
                    sV4WrappedDrawableGetM = sV4WrappedDrawableClass.getDeclaredMethod("getWrappedDrawable");
                    sV4WrappedDrawableGetM.setAccessible(true);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "getV4WrappedDrawableWrappedDrawable No Such Method");
                    }
                }
            }
            if (sV4WrappedDrawableGetM != null) {
                try {
                    return (Drawable) sV4WrappedDrawableGetM.invoke(drawable);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "getV4WrappedDrawableWrappedDrawable invoke error: " + e);
                    }
                }
            }
        }
        return drawable;
    }

    public static void setV4WrappedDrawableWrappedDrawable(Drawable drawable, Drawable inner) {
        if (sV4WrappedDrawableClass != null) {
            if (sV4WrappedDrawableSetM == null) {
                try {
                    sV4WrappedDrawableSetM = sV4WrappedDrawableClass.getDeclaredMethod("setWrappedDrawable", Drawable.class);
                    sV4WrappedDrawableSetM.setAccessible(true);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "setV4WrappedDrawableWrappedDrawable No Such Method");
                    }
                }
            }
            if (sV4WrappedDrawableSetM != null) {
                try {
                    sV4WrappedDrawableSetM.invoke(drawable, inner);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "setV4WrappedDrawableWrappedDrawable invoke error: " + e);
                    }
                }
            }
        }
    }

    public static boolean hasV4DrawableWrapper() {
        return sV4DrawableWrapperClass != null;
    }

    public static boolean isV4DrawableWrapper(Drawable drawable) {
        return sV4DrawableWrapperClass != null
                && sV4DrawableWrapperClass.isAssignableFrom(drawable.getClass());
    }

    public static Drawable getV4DrawableWrapperWrappedDrawable(Drawable drawable) {
        if (sV4DrawableWrapperClass != null) {
            if (sV4DrawableWrapperGetM == null) {
                try {
                    sV4DrawableWrapperGetM = sV4DrawableWrapperClass.getDeclaredMethod("getWrappedDrawable");
                    sV4DrawableWrapperGetM.setAccessible(true);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "getV4DrawableWrapperWrappedDrawable No Such Method");
                    }
                }
            }
            if (sV4DrawableWrapperGetM != null) {
                try {
                    return (Drawable) sV4DrawableWrapperGetM.invoke(drawable);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "getV4DrawableWrapperWrappedDrawable invoke error: " + e);
                    }
                }
            }
        }
        return drawable;
    }

    public static void setV4DrawableWrapperWrappedDrawable(Drawable drawable, Drawable inner) {
        if (sV4DrawableWrapperClass != null) {
            if (sV4DrawableWrapperSetM == null) {
                try {
                    sV4DrawableWrapperSetM = sV4DrawableWrapperClass.getDeclaredMethod("setWrappedDrawable", Drawable.class);
                    sV4DrawableWrapperSetM.setAccessible(true);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "setV4DrawableWrapperWrappedDrawable No Such Method");
                    }
                }
            }
            if (sV4DrawableWrapperSetM != null) {
                try {
                    sV4DrawableWrapperSetM.invoke(drawable, inner);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "setV4DrawableWrapperWrappedDrawable invoke error: " + e);
                    }
                }
            }
        }
    }

}
