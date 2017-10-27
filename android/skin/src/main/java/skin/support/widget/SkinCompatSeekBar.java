package skin.support.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

import skin.support.R;

/**
 * Created by ximsfei on 17-1-21.
 */

public class SkinCompatSeekBar extends SeekBar implements SkinCompatSupportable {
    private SkinCompatProgressBarHelper mSkinCompatSeekBarHelper;

    public SkinCompatSeekBar(Context context) {
        this(context, null);
    }

    public SkinCompatSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, R.attr.seekBarStyle);
    }

    public SkinCompatSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mSkinCompatSeekBarHelper = new SkinCompatProgressBarHelper(this);
        mSkinCompatSeekBarHelper.loadFromAttributes(attrs, defStyleAttr);
    }


    @Override
    public void applySkin() {
        if (mSkinCompatSeekBarHelper != null) {
            mSkinCompatSeekBarHelper.applySkin();
        }
    }

}
