package skin.support.load;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

import java.io.File;

import skin.support.SkinCompatManager;
import skin.support.SkinCompatManager.SkinLoaderStrategy;
import skin.support.content.res.SkinCompatResources;
import skin.support.utils.SkinFileUtils;

public class SkinSDCardLoader implements SkinLoaderStrategy {
    @Override
    public String loadSkinInBackground(Context context, String skinName) {
        if (!TextUtils.isEmpty(skinName)) {
            String skinPkgPath = getSkinPath(context, skinName);
            if (SkinFileUtils.isFileExists(skinPkgPath)) {
                String pkgName = SkinCompatManager.getInstance().getSkinPackageName(skinPkgPath);
                Resources resources = SkinCompatManager.getInstance().getSkinResources(skinPkgPath);
                if (resources != null && !TextUtils.isEmpty(pkgName)) {
                    SkinCompatResources.getInstance().setupSkin(
                            resources,
                            pkgName,
                            skinName,
                            this);
                    return skinName;
                }
            }
        }
        return null;
    }

    public String getSkinPath(Context context, String skinName) {
        String sdcard = SkinCompatManager.getInstance().getSDCardPath();
        if (TextUtils.isEmpty(sdcard)) {
            sdcard = SkinFileUtils.getSkinDir(context);
        }
        return new File(sdcard, skinName).getAbsolutePath();
    }

    @Override
    public String getTargetResourceEntryName(Context context, String skinName, int resId) {
        return null;
    }

    @Override
    public int getType() {
        return SkinCompatManager.SKIN_LOADER_STRATEGY_SDCARD;
    }
}
