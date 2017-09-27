package skin.support.load;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

import skin.support.SkinCompatManager;
import skin.support.SkinCompatManager.SkinLoaderStrategy;
import skin.support.content.res.SkinCompatResources;
import skin.support.utils.SkinFileUtils;

public abstract class SkinSDCardLoader implements SkinLoaderStrategy {
    @Override
    public String loadSkinInBackground(Context context, String skinName) {
        String skinPkgPath = getSkinPath(context, skinName);
        if (SkinFileUtils.isFileExists(skinPkgPath)) {
            String pkgName = SkinCompatManager.getInstance(context).getSkinPackageName(skinPkgPath);
            Resources resources = SkinCompatManager.getInstance(context).getSkinResources(skinPkgPath);
            if (resources != null && !TextUtils.isEmpty(pkgName)) {
                SkinCompatResources.getInstance(context).setupSkin(
                        resources,
                        pkgName,
                        skinName,
                        this);
                return skinName;
            }
        }
        return null;
    }

    protected abstract String getSkinPath(Context context, String skinName);

    @Override
    public String getTargetResourceEntryName(Context context, String skinName, int resId) {
        return null;
    }
}
