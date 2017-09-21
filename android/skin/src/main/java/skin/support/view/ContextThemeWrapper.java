package skin.support.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;

public class ContextThemeWrapper extends ContextWrapper {
    private int mThemeResource;
    private Resources.Theme mTheme;
    private LayoutInflater mInflater;

    public ContextThemeWrapper(Context base, @StyleRes int themeResId) {
        super(base);
        mThemeResource = themeResId;
    }

    public ContextThemeWrapper(Context base, Resources.Theme theme) {
        super(base);
        mTheme = theme;
    }

    @Override
    public void setTheme(int resid) {
        if (mThemeResource != resid) {
            mThemeResource = resid;
            initializeTheme();
        }
    }

    public int getThemeResId() {
        return mThemeResource;
    }

    @Override
    public Resources.Theme getTheme() {
        if (mTheme != null) {
            return mTheme;
        }

        if (mThemeResource == 0) {
            mThemeResource = android.R.style.Theme_Holo_Light;
        }
        initializeTheme();

        return mTheme;
    }

    @Override
    public Object getSystemService(String name) {
        if (LAYOUT_INFLATER_SERVICE.equals(name)) {
            if (mInflater == null) {
                mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
            }
            return mInflater;
        }
        return getBaseContext().getSystemService(name);
    }

    /**
     * Called by {@link #setTheme} and {@link #getTheme} to apply a theme
     * resource to the current Theme object.  Can override to change the
     * default (simple) behavior.  This method will not be called in multiple
     * threads simultaneously.
     *
     * @param theme The Theme object being modified.
     * @param resid The theme style resource being applied to <var>theme</var>.
     * @param first Set to true if this is the first time a style is being
     *              applied to <var>theme</var>.
     */
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        theme.applyStyle(resid, true);
    }

    private void initializeTheme() {
        final boolean first = mTheme == null;
        if (first) {
            mTheme = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                mTheme.setTo(theme);
            }
        }
        onApplyThemeResource(mTheme, mThemeResource, first);
    }
}