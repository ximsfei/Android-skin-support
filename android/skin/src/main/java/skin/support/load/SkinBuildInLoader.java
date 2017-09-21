package skin.support.load;

import android.content.Context;

import skin.support.SkinCompatManager;
import skin.support.SkinCompatManager.SkinLoaderStrategy;
import skin.support.content.res.SkinCompatResources;

public class SkinBuildInLoader implements SkinLoaderStrategy {
    @Override
    public String loadSkinInBackground(Context context, String skinName) {
        SkinCompatResources.getInstance().setupSkin(
                context.getResources(),
                context.getPackageName(),
                skinName,
                this);
        return skinName;
    }

    @Override
    public String getTargetResourceEntryName(Context context, String skinName, int resId) {
        return context.getResources().getResourceEntryName(resId) + "_" + skinName;
    }

    @Override
    public int getType() {
        return SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN;
    }
}
