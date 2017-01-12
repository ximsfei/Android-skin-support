package skin.support.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import skin.support.widget.SkinCompatButton;
import skin.support.widget.SkinCompatEditText;
import skin.support.widget.SkinCompatImageView;
import skin.support.widget.SkinCompatTextView;
import skin.support.widget.SkinCompatToolbar;

/**
 * Created by ximsfei on 17-1-9.
 */

public class SkinCompatViewInflater {
    private static final Class<?>[] sConstructorSignature = new Class[]{
            Context.class, AttributeSet.class};
    private static final int[] sOnClickAttrs = new int[]{android.R.attr.onClick};

    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.view.",
            "android.webkit."
    };

    private static final String LOG_TAG = "SkinCompatViewInflater";

    private static final Map<String, Constructor<? extends View>> sConstructorMap
            = new ArrayMap<>();

    private final Object[] mConstructorArgs = new Object[2];

    public final View createView(View parent, final String name, @NonNull Context context,
                                 @NonNull AttributeSet attrs, boolean inheritContext,
                                 boolean readAndroidTheme, boolean readAppTheme, boolean wrapContext) {
        final Context originalContext = context;

        // We can emulate Lollipop's android:theme attribute propagating down the view hierarchy
        // by using the parent's context
        if (inheritContext && parent != null) {
            context = parent.getContext();
        }
        if (readAndroidTheme || readAppTheme) {
            // We then apply the theme on the context, if specified
            context = themifyContext(context, attrs, readAndroidTheme, readAppTheme);
        }
        if (wrapContext) {
            context = TintContextWrapper.wrap(context);
        }

        View view = null;

        // We need to 'inject' our tint aware Views in place of the standard framework versions
        switch (name) {
            case "android.support.v7.widget.Toolbar":
                view = new SkinCompatToolbar(context, attrs);
                break;
            case "TextView":
                view = new SkinCompatTextView(context, attrs);
                break;
            case "ImageView":
                view = new SkinCompatImageView(context, attrs);
                break;
            case "Button":
                view = new SkinCompatButton(context, attrs);
                break;
            case "EditText":
                view = new SkinCompatEditText(context, attrs);
                break;
            case "Spinner":
                view = new AppCompatSpinner(context, attrs);
                break;
            case "ImageButton":
                view = new AppCompatImageButton(context, attrs);
                break;
            case "CheckBox":
                view = new AppCompatCheckBox(context, attrs);
                break;
            case "RadioButton":
                view = new AppCompatRadioButton(context, attrs);
                break;
            case "CheckedTextView":
                view = new AppCompatCheckedTextView(context, attrs);
                break;
            case "AutoCompleteTextView":
                view = new AppCompatAutoCompleteTextView(context, attrs);
                break;
            case "MultiAutoCompleteTextView":
                view = new AppCompatMultiAutoCompleteTextView(context, attrs);
                break;
            case "RatingBar":
                view = new AppCompatRatingBar(context, attrs);
                break;
            case "SeekBar":
                view = new AppCompatSeekBar(context, attrs);
                break;
        }

        if (view == null && originalContext != context) {
            // If the original context does not equal our themed context, then we need to manually
            // inflate it using the name so that android:theme takes effect.
            view = createViewFromTag(context, name, attrs);
        }

        if (view != null) {
            // If we have created a view, check it's android:onClick
            checkOnClickListener(view, attrs);
        }

        return view;
    }

    public View createViewFromTag(Context context, String name, AttributeSet attrs) {
        if (name.equals("view")) {
            name = attrs.getAttributeValue(null, "class");
        }

        try {
            mConstructorArgs[0] = context;
            mConstructorArgs[1] = attrs;

            if (-1 == name.indexOf('.')) {
                for (int i = 0; i < sClassPrefixList.length; i++) {
                    final View view = createView(context, name, sClassPrefixList[i]);
                    if (view != null) {
                        return view;
                    }
                }
                return null;
            } else {
                return createView(context, name, null);
            }
        } catch (Exception e) {
            // We do not want to catch these, lets return null and let the actual LayoutInflater
            // try
            return null;
        } finally {
            // Don't retain references on context.
            mConstructorArgs[0] = null;
            mConstructorArgs[1] = null;
        }
    }

    /**
     * android:onClick doesn't handle views with a ContextWrapper context. This method
     * backports new framework functionality to traverse the Context wrappers to find a
     * suitable target.
     */
    private void checkOnClickListener(View view, AttributeSet attrs) {
        final Context context = view.getContext();

        if (!(context instanceof ContextWrapper) ||
                (Build.VERSION.SDK_INT >= 15 && !ViewCompat.hasOnClickListeners(view))) {
            // Skip our compat functionality if: the Context isn't a ContextWrapper, or
            // the view doesn't have an OnClickListener (we can only rely on this on API 15+ so
            // always use our compat code on older devices)
            return;
        }

        final TypedArray a = context.obtainStyledAttributes(attrs, sOnClickAttrs);
        final String handlerName = a.getString(0);
        if (handlerName != null) {
            view.setOnClickListener(new DeclaredOnClickListener(view, handlerName));
        }
        a.recycle();
    }

    private View createView(Context context, String name, String prefix)
            throws ClassNotFoundException, InflateException {
        Constructor<? extends View> constructor = sConstructorMap.get(name);

        try {
            if (constructor == null) {
                // Class not found in the cache, see if it's real, and try to add it
                Class<? extends View> clazz = context.getClassLoader().loadClass(
                        prefix != null ? (prefix + name) : name).asSubclass(View.class);

                constructor = clazz.getConstructor(sConstructorSignature);
                sConstructorMap.put(name, constructor);
            }
            constructor.setAccessible(true);
            return constructor.newInstance(mConstructorArgs);
        } catch (Exception e) {
            // We do not want to catch these, lets return null and let the actual LayoutInflater
            // try
            return null;
        }
    }

    /**
     * Allows us to emulate the {@code android:theme} attribute for devices before L.
     */
    private static Context themifyContext(Context context, AttributeSet attrs,
                                          boolean useAndroidTheme, boolean useAppTheme) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.View, 0, 0);
        int themeId = 0;
        if (useAndroidTheme) {
            // First try reading android:theme if enabled
            themeId = a.getResourceId(R.styleable.View_android_theme, 0);
        }
        if (useAppTheme && themeId == 0) {
            // ...if that didn't work, try reading app:theme (for legacy reasons) if enabled
            themeId = a.getResourceId(R.styleable.View_theme, 0);

            if (themeId != 0) {
                Log.i(LOG_TAG, "app:theme is now deprecated. "
                        + "Please move to using android:theme instead.");
            }
        }
        a.recycle();

        if (themeId != 0 && (!(context instanceof ContextThemeWrapper)
                || ((ContextThemeWrapper) context).getThemeResId() != themeId)) {
            // If the context isn't a ContextThemeWrapper, or it is but does not have
            // the same theme as we need, wrap it in a new wrapper
            context = new ContextThemeWrapper(context, themeId);
        }
        return context;
    }

    /**
     * An implementation of OnClickListener that attempts to lazily load a
     * named click handling method from a parent or ancestor context.
     */
    private static class DeclaredOnClickListener implements View.OnClickListener {
        private final View mHostView;
        private final String mMethodName;

        private Method mResolvedMethod;
        private Context mResolvedContext;

        public DeclaredOnClickListener(@NonNull View hostView, @NonNull String methodName) {
            mHostView = hostView;
            mMethodName = methodName;
        }

        @Override
        public void onClick(@NonNull View v) {
            if (mResolvedMethod == null) {
                resolveMethod(mHostView.getContext(), mMethodName);
            }

            try {
                mResolvedMethod.invoke(mResolvedContext, v);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(
                        "Could not execute non-public method for android:onClick", e);
            } catch (InvocationTargetException e) {
                throw new IllegalStateException(
                        "Could not execute method for android:onClick", e);
            }
        }

        @NonNull
        private void resolveMethod(@Nullable Context context, @NonNull String name) {
            while (context != null) {
                try {
                    if (!context.isRestricted()) {
                        final Method method = context.getClass().getMethod(mMethodName, View.class);
                        if (method != null) {
                            mResolvedMethod = method;
                            mResolvedContext = context;
                            return;
                        }
                    }
                } catch (NoSuchMethodException e) {
                    // Failed to find method, keep searching up the hierarchy.
                }

                if (context instanceof ContextWrapper) {
                    context = ((ContextWrapper) context).getBaseContext();
                } else {
                    // Can't search up the hierarchy, null out and fail.
                    context = null;
                }
            }

            final int id = mHostView.getId();
            final String idText = id == View.NO_ID ? "" : " with id '"
                    + mHostView.getContext().getResources().getResourceEntryName(id) + "'";
            throw new IllegalStateException("Could not find method " + mMethodName
                    + "(View) in a parent or ancestor Context for android:onClick "
                    + "attribute defined on view " + mHostView.getClass() + idText);
        }
    }
}
