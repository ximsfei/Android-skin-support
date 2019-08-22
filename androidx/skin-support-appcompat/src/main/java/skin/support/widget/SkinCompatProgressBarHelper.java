package skin.support.widget;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ProgressBar;

import skin.support.appcompat.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.content.res.SkinCompatVectorResources;
import skin.support.utils.SkinCompatVersionUtils;

/**
 * Created by ximsfei on 2017/1/20.
 */

public class SkinCompatProgressBarHelper extends SkinCompatHelper {

    private final ProgressBar mView;

    private Bitmap mSampleTile;
    private int mIndeterminateDrawableResId = INVALID_ID;
    private int mProgressDrawableResId = INVALID_ID;
    private int mIndeterminateTintResId = INVALID_ID;

    SkinCompatProgressBarHelper(ProgressBar view) {
        mView = view;
    }

    void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = mView.getContext().obtainStyledAttributes(attrs, R.styleable.SkinCompatProgressBar, defStyleAttr, 0);

        mIndeterminateDrawableResId = a.getResourceId(R.styleable.SkinCompatProgressBar_android_indeterminateDrawable, INVALID_ID);
        mProgressDrawableResId = a.getResourceId(R.styleable.SkinCompatProgressBar_android_progressDrawable, INVALID_ID);

        a.recycle();
        if (Build.VERSION.SDK_INT > 21) {
            a = mView.getContext().obtainStyledAttributes(attrs, new int[]{android.R.attr.indeterminateTint}, defStyleAttr, 0);
            mIndeterminateTintResId = a.getResourceId(0, INVALID_ID);
            a.recycle();
        }
        applySkin();
    }

    /**
     * Converts a drawable to a tiled version of itself. It will recursively
     * traverse layer and state list drawables.
     */
    private Drawable tileify(Drawable drawable, boolean clip) {
        if (SkinCompatVersionUtils.isV4WrappedDrawable(drawable)) {
            Drawable inner = SkinCompatVersionUtils.getV4WrappedDrawableWrappedDrawable(drawable);
            if (inner != null) {
                inner = tileify(inner, clip);
                SkinCompatVersionUtils.setV4WrappedDrawableWrappedDrawable(drawable, inner);
            }
        } else if (SkinCompatVersionUtils.isV4DrawableWrapper(drawable)) {
            Drawable inner = SkinCompatVersionUtils.getV4DrawableWrapperWrappedDrawable(drawable);
            if (inner != null) {
                inner = tileify(inner, clip);
                SkinCompatVersionUtils.setV4DrawableWrapperWrappedDrawable(drawable, inner);
            }
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable background = (LayerDrawable) drawable;
            final int N = background.getNumberOfLayers();
            Drawable[] outDrawables = new Drawable[N];

            for (int i = 0; i < N; i++) {
                int id = background.getId(i);
                outDrawables[i] = tileify(background.getDrawable(i),
                        (id == android.R.id.progress || id == android.R.id.secondaryProgress));
            }
            LayerDrawable newBg = new LayerDrawable(outDrawables);

            for (int i = 0; i < N; i++) {
                newBg.setId(i, background.getId(i));
            }

            return newBg;

        } else if (drawable instanceof BitmapDrawable) {
            final BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            final Bitmap tileBitmap = bitmapDrawable.getBitmap();
            if (mSampleTile == null) {
                mSampleTile = tileBitmap;
            }

            final ShapeDrawable shapeDrawable = new ShapeDrawable(getDrawableShape());
            final BitmapShader bitmapShader = new BitmapShader(tileBitmap,
                    Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
            shapeDrawable.getPaint().setShader(bitmapShader);
            shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            return (clip) ? new ClipDrawable(shapeDrawable, Gravity.LEFT,
                    ClipDrawable.HORIZONTAL) : shapeDrawable;
        }

        return drawable;
    }

    /**
     * Convert a AnimationDrawable for use as a barberpole animation.
     * Each frame of the animation is wrapped in a ClipDrawable and
     * given a tiling BitmapShader.
     */
    private Drawable tileifyIndeterminate(Drawable drawable) {
        if (drawable instanceof AnimationDrawable) {
            AnimationDrawable background = (AnimationDrawable) drawable;
            final int N = background.getNumberOfFrames();
            AnimationDrawable newBg = new AnimationDrawable();
            newBg.setOneShot(background.isOneShot());

            for (int i = 0; i < N; i++) {
                Drawable frame = tileify(background.getFrame(i), true);
                frame.setLevel(10000);
                newBg.addFrame(frame, background.getDuration(i));
            }
            newBg.setLevel(10000);
            drawable = newBg;
        }
        return drawable;
    }

    private Shape getDrawableShape() {
        final float[] roundedCorners = new float[]{5, 5, 5, 5, 5, 5, 5, 5};
        return new RoundRectShape(roundedCorners, null, null);
    }

    @Override
    public void applySkin() {
        mIndeterminateDrawableResId = checkResourceId(mIndeterminateDrawableResId);
        if (mIndeterminateDrawableResId != INVALID_ID) {
            Drawable drawable = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mIndeterminateDrawableResId);
            drawable.setBounds(mView.getIndeterminateDrawable().getBounds());
            mView.setIndeterminateDrawable(tileifyIndeterminate(drawable));
        }

        mProgressDrawableResId = checkProgressDrawableResId(mProgressDrawableResId);
        if (mProgressDrawableResId != INVALID_ID) {
            Drawable drawable = SkinCompatVectorResources.getDrawableCompat(mView.getContext(), mProgressDrawableResId);
            mView.setProgressDrawable(tileify(drawable, false));
        }
        if (Build.VERSION.SDK_INT > 21) {
            mIndeterminateTintResId = checkResourceId(mIndeterminateTintResId);
            if (mIndeterminateTintResId != INVALID_ID) {
                mView.setIndeterminateTintList(SkinCompatResources.getColorStateList(mView.getContext(), mIndeterminateTintResId));
            }
        }
    }

    private int checkProgressDrawableResId(int mProgressDrawableResId) {
        return checkResourceId(mProgressDrawableResId);
    }
}
