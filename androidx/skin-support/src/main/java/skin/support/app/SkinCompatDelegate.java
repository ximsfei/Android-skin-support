package skin.support.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import skin.support.SkinCompatManager;
import skin.support.annotation.NonNull;
import skin.support.widget.SkinCompatSupportable;

/**
 * Created by ximsfei on 2017/1/9.
 */

public class SkinCompatDelegate implements LayoutInflater.Factory2 {
    private final Context mContext;
    private SkinCompatViewInflater mSkinCompatViewInflater;
    private List<WeakReference<SkinCompatSupportable>> mSkinHelpers = new CopyOnWriteArrayList<>();

    private SkinCompatDelegate(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = createView(parent, name, context, attrs);

        if (view == null) {
            return null;
        }
        if (view instanceof SkinCompatSupportable) {
            mSkinHelpers.add(new WeakReference<>((SkinCompatSupportable) view));
        }

        return view;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = createView(null, name, context, attrs);

        if (view == null) {
            return null;
        }
        if (view instanceof SkinCompatSupportable) {
            mSkinHelpers.add(new WeakReference<>((SkinCompatSupportable) view));
        }

        return view;
    }

    public View createView(View parent, final String name, @NonNull Context context,
                           @NonNull AttributeSet attrs) {
        if (mSkinCompatViewInflater == null) {
            mSkinCompatViewInflater = new SkinCompatViewInflater();
        }

        List<SkinWrapper> wrapperList = SkinCompatManager.getInstance().getWrappers();
        for (SkinWrapper wrapper : wrapperList) {
            Context wrappedContext = wrapper.wrapContext(mContext, parent, attrs);
            if (wrappedContext != null) {
                context = wrappedContext;
            }
        }
        return mSkinCompatViewInflater.createView(parent, name, context, attrs);
    }

    public static SkinCompatDelegate create(Context context) {
        return new SkinCompatDelegate(context);
    }

    public void applySkin() {
        if (mSkinHelpers != null && !mSkinHelpers.isEmpty()) {
            for (WeakReference ref : mSkinHelpers) {
                if (ref != null && ref.get() != null) {
                    ((SkinCompatSupportable) ref.get()).applySkin();
                }
            }
        }
    }
}
