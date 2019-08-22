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

    private static Class<?> sV7DrawableWrapperClass;
    private static Method sV7DrawableWrapperGetM;
    private static Method sV7DrawableWrapperSetM;

    static {
        try {
            sV4WrappedDrawableClass = Class.forName("android.support.v4.graphics.drawable.WrappedDrawable");
        } catch (ClassNotFoundException e) {
            if (Slog.DEBUG) {
                Slog.i(TAG, "hasV4WrappedDrawable = false");
            }
        }
        try {
            sV4DrawableWrapperClass = Class.forName("android.support.v4.graphics.drawable.DrawableWrapper");
        } catch (ClassNotFoundException e) {
            if (Slog.DEBUG) {
                Slog.i(TAG, "hasV4DrawableWrapper = false");
            }
        }
        try {
            sV7DrawableWrapperClass = Class.forName("android.support.v7.graphics.drawable.DrawableWrapper");
        } catch (ClassNotFoundException e) {
            if (Slog.DEBUG) {
                Slog.i(TAG, "hasV7DrawableWrapper = false");
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

    public static boolean hasV7DrawableWrapper() {
        return sV7DrawableWrapperClass != null;
    }

    public static boolean isV7DrawableWrapper(Drawable drawable) {
        return sV7DrawableWrapperClass != null
                && sV7DrawableWrapperClass.isAssignableFrom(drawable.getClass());
    }

    public static Drawable getV7DrawableWrapperWrappedDrawable(Drawable drawable) {
        if (sV7DrawableWrapperClass != null) {
            if (sV7DrawableWrapperGetM == null) {
                try {
                    sV7DrawableWrapperGetM = sV7DrawableWrapperClass.getDeclaredMethod("getWrappedDrawable");
                    sV7DrawableWrapperGetM.setAccessible(true);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "getV7DrawableWrapperWrappedDrawable No Such Method");
                    }
                }
            }
            if (sV7DrawableWrapperGetM != null) {
                try {
                    return (Drawable) sV7DrawableWrapperGetM.invoke(drawable);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "getV7DrawableWrapperWrappedDrawable invoke error: " + e);
                    }
                }
            }
        }
        return drawable;
    }

    public static void setV7DrawableWrapperWrappedDrawable(Drawable drawable, Drawable inner) {
        if (sV7DrawableWrapperClass != null) {
            if (sV7DrawableWrapperSetM == null) {
                try {
                    sV7DrawableWrapperSetM = sV7DrawableWrapperClass.getDeclaredMethod("setWrappedDrawable", Drawable.class);
                    sV7DrawableWrapperSetM.setAccessible(true);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "setV7DrawableWrapperWrappedDrawable No Such Method");
                    }
                }
            }
            if (sV7DrawableWrapperSetM != null) {
                try {
                    sV7DrawableWrapperSetM.invoke(drawable, inner);
                } catch (Exception e) {
                    if (Slog.DEBUG) {
                        Slog.i(TAG, "setV7DrawableWrapperWrappedDrawable invoke error: " + e);
                    }
                }
            }
        }
    }

}
