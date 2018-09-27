package skin.support.app;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import skin.support.SkinCompatManager;
import skin.support.widget.SkinCompatSupportable;

/**
 * Created by ximsfei on 2017/1/9.
 */

public class SkinCompatDelegate implements LayoutInflaterFactory {
    private final Context mContext;
    private SkinCompatViewInflater mSkinCompatViewInflater;
    private List<WeakReference<SkinCompatSupportable>> mSkinHelpers = new ArrayList<>();

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
