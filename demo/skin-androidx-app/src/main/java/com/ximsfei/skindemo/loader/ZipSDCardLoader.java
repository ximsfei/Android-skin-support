package com.ximsfei.skindemo.loader;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import skin.support.load.SkinSDCardLoader;
import skin.support.utils.SkinFileUtils;

public class ZipSDCardLoader extends SkinSDCardLoader {
    public static final int SKIN_LOADER_STRATEGY_ZIP = Integer.MAX_VALUE - 1;

    @Override
    public String loadSkinInBackground(Context context, String skinName) {
        try {
            InputStream is = context.getAssets().open("zip_res.zip");
            String dir = SkinFileUtils.getSkinDir(context);
            File zipFile = new File(dir, "zip_res.zip");
            OutputStream os = new FileOutputStream(zipFile);
            int byteCount;
            byte[] bytes = new byte[1024];
            while ((byteCount = is.read(bytes)) != -1) {
                os.write(bytes, 0, byteCount);
            }
            os.close();
            is.close();
            unzipBackgroundPng(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.loadSkinInBackground(context, skinName);
    }

    private void unzipBackgroundPng(String dir) throws IOException {
        File zipBackground = new File(dir, "zip_background.png");
        ZipFile zipFile = new ZipFile(new File(dir, "zip_res.zip"));
        ZipEntry entry = zipFile.getEntry("zip_background.png");
        InputStream is = zipFile.getInputStream(entry);
        OutputStream os = new FileOutputStream(zipBackground);
        int byteCount;
        byte[] bytes = new byte[1024];
        while ((byteCount = is.read(bytes)) != -1) {
            os.write(bytes, 0, byteCount);
        }
        os.close();
        is.close();
    }

    @Override
    protected String getSkinPath(Context context, String skinName) {
        return new File(SkinFileUtils.getSkinDir(context), skinName).getAbsolutePath();
    }

    @Override
    public Drawable getDrawable(Context context, String skinName, int resId) {
        String resName = context.getResources().getResourceEntryName(resId);
        String resType = context.getResources().getResourceTypeName(resId);
        if ("drawable".equalsIgnoreCase(resType) && "zip_background".equalsIgnoreCase(resName)) {
            String dir = SkinFileUtils.getSkinDir(context);
            File zipBackground = new File(dir, "zip_background.png");
            if (zipBackground.exists()) {
                return Drawable.createFromPath(zipBackground.getAbsolutePath());
            }
            return null;
        }
        return super.getDrawable(context, skinName, resId);
    }

    @Override
    public int getType() {
        return SKIN_LOADER_STRATEGY_ZIP;
    }
}
