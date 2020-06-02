package skin.support.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Field;

public final class LayoutInflaterCompat {
    private static final String TAG = "LayoutInflaterCompatHC";

    private static Field sLayoutInflaterFactory2Field;
    private static boolean sCheckedField;

    @SuppressWarnings("deprecation")
    static class Factory2Wrapper implements LayoutInflater.Factory2 {
        final LayoutInflaterFactory mDelegateFactory;

        Factory2Wrapper(LayoutInflaterFactory delegateFactory) {
            mDelegateFactory = delegateFactory;
        }

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return mDelegateFactory.onCreateView(null, name, context, attrs);
        }

        @Override
        public View onCreateView(View parent, String name, Context context,
                                 AttributeSet attributeSet) {
            return mDelegateFactory.onCreateView(parent, name, context, attributeSet);
        }

        @Override
        public String toString() {
            return getClass().getName() + "{" + mDelegateFactory + "}";
        }
    }

    /**
     * For APIs < 21, there was a framework bug that prevented a LayoutInflater's
     * Factory2 from being merged properly if set after a cloneInContext from a LayoutInflater
     * that already had a Factory2 registered. We work around that bug here. If we can't we
     * log an error.
     */
    private static void forceSetFactory2(LayoutInflater inflater, LayoutInflater.Factory2 factory) {
        if (!sCheckedField) {
            try {
                sLayoutInflaterFactory2Field = LayoutInflater.class.getDeclaredField("mFactory2");
                sLayoutInflaterFactory2Field.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.e(TAG, "forceSetFactory2 Could not find field 'mFactory2' on class "
                        + LayoutInflater.class.getName()
                        + "; inflation may have unexpected results.", e);
            }
            sCheckedField = true;
        }
        if (sLayoutInflaterFactory2Field != null) {
            try {
                sLayoutInflaterFactory2Field.set(inflater, factory);
            } catch (IllegalAccessException e) {
                Log.e(TAG, "forceSetFactory2 could not set the Factory2 on LayoutInflater "
                        + inflater + "; inflation may have unexpected results.", e);
            }
        }
    }

    /*
     * Hide the constructor.
     */
    private LayoutInflaterCompat() {
    }

    /**
     * Attach a custom Factory interface for creating views while using
     * this LayoutInflater. This must not be null, and can only be set once;
     * after setting, you can not change the factory.
     *
     * @see LayoutInflater#setFactory(android.view.LayoutInflater.Factory)
     * @deprecated Use {@link #setFactory2(LayoutInflater, LayoutInflater.Factory2)} instead to set
     * and {@link LayoutInflater#getFactory2()} to get the factory.
     */
    @Deprecated
    public static void setFactory(
            LayoutInflater inflater, LayoutInflaterFactory factory) {
        if (Build.VERSION.SDK_INT >= 21) {
            inflater.setFactory2(factory != null ? new LayoutInflaterCompat.Factory2Wrapper(factory) : null);
        } else {
            final LayoutInflater.Factory2 factory2 = factory != null
                    ? new LayoutInflaterCompat.Factory2Wrapper(factory) : null;
            inflater.setFactory2(factory2);

            final LayoutInflater.Factory f = inflater.getFactory();
            if (f instanceof LayoutInflater.Factory2) {
                // The merged factory is now set to getFactory(), but not getFactory2() (pre-v21).
                // We will now try and force set the merged factory to mFactory2
                forceSetFactory2(inflater, (LayoutInflater.Factory2) f);
            } else {
                // Else, we will force set the original wrapped Factory2
                forceSetFactory2(inflater, factory2);
            }
        }
    }

    /**
     * Attach a custom {@link LayoutInflater.Factory2} for creating views while using
     * this {@link LayoutInflater}. This must not be null, and can only be set once;
     * after setting, you can not change the factory.
     *
     * @see LayoutInflater#setFactory2(android.view.LayoutInflater.Factory2)
     */
    public static void setFactory2(
            LayoutInflater inflater, LayoutInflater.Factory2 factory) {
        inflater.setFactory2(factory);

        if (Build.VERSION.SDK_INT < 21) {
            final LayoutInflater.Factory f = inflater.getFactory();
            if (f instanceof LayoutInflater.Factory2) {
                // The merged factory is now set to getFactory(), but not getFactory2() (pre-v21).
                // We will now try and force set the merged factory to mFactory2
                forceSetFactory2(inflater, (LayoutInflater.Factory2) f);
            } else {
                // Else, we will force set the original wrapped Factory2
                forceSetFactory2(inflater, factory);
            }
        }
    }

    /**
     * Return the current {@link LayoutInflaterFactory} (or null). This is
     * called on each element name. If the factory returns a View, add that
     * to the hierarchy. If it returns null, proceed to call onCreateView(name).
     *
     * @return The {@link LayoutInflaterFactory} associated with the
     * {@link LayoutInflater}. Will be {@code null} if the inflater does not
     * have a {@link LayoutInflaterFactory} but a raw {@link LayoutInflater.Factory}.
     * @see LayoutInflater#getFactory()
     * @deprecated Use {@link #setFactory2(LayoutInflater, LayoutInflater.Factory2)} to set and
     * {@link LayoutInflater#getFactory2()} to get the factory.
     */
    @Deprecated
    public static LayoutInflaterFactory getFactory(LayoutInflater inflater) {
        LayoutInflater.Factory factory = inflater.getFactory();
        if (factory instanceof LayoutInflaterCompat.Factory2Wrapper) {
            return ((LayoutInflaterCompat.Factory2Wrapper) factory).mDelegateFactory;
        }
        return null;
    }
}