package com.afei.skinzipdemo.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;

import com.afei.skinzipdemo.MainActivity;
import com.afei.skinzipdemo.skin.ZipSDCardLoader;

import java.io.File;


/**
 * Created by mac on 2018/6/25.
 */

public class SkinTool {

    private static SkinTool skinTool;

    private SkinTool() {
    }

    public static SkinTool init() {
        if (skinTool != null) {
            return skinTool;
        }
        return skinTool = new SkinTool();
    }


    /**
     * 动态获取目前的主题图片
     *
     * @param context
     * @param rId
     * @return
     */
    public Drawable getDrawable(Context context, int rId) {
        Drawable drawable = context.getResources().getDrawable(rId);
        String resName = context.getResources().getResourceEntryName(rId);
        String resType = context.getResources().getResourceTypeName(rId);
        if ("drawable".equalsIgnoreCase(resType) || "mipmap".equalsIgnoreCase(resType)) {
            String folder = MainActivity.sDAppTheme + File.separator + "img";
            Drawable nowDrawable = ZipSDCardLoader.get(rId);
            if (nowDrawable != null) {
                return nowDrawable;
            }
            File zipBackgroundPNG = new File(folder, resName + ".png");
            if (zipBackgroundPNG.exists()) {
                nowDrawable = ZipSDCardLoader.readFileImage(context, zipBackgroundPNG.getAbsolutePath());
                ZipSDCardLoader.add(rId, nowDrawable);
            }
            File zipBackgroundJPG = new File(folder, resName + ".jpg");
            if (zipBackgroundJPG.exists()) {
                nowDrawable = ZipSDCardLoader.readFileImage(context, zipBackgroundJPG.getAbsolutePath());
                ZipSDCardLoader.add(rId, nowDrawable);
            }
//            File zipBackgroundGIF = new File(dir, resName + ".gif");
//            if (zipBackgroundGIF.exists()) {
//                try {
//                    nowDrawable = new GifDrawable(context,zipBackgroundGIF.getAbsolutePath());
//                    ZipSDCardLoader.add(rId, nowDrawable);
//                } catch (Exception e) {
//                    nowDrawable = ZipSDCardLoader.readFileImage(context,zipBackgroundGIF.getAbsolutePath());
//                    ZipSDCardLoader.add(rId, nowDrawable);
//                }
//            }
            if (nowDrawable != null) return nowDrawable;
        }
        return drawable;
    }

    /**
     * 判断该主题图片 是否存在
     *
     * @param context
     * @param rId
     * @return
     */
    public boolean isSkin(Context context, int rId) {
        String resName = context.getResources().getResourceEntryName(rId);
        String resType = context.getResources().getResourceTypeName(rId);
        if ("drawable".equalsIgnoreCase(resType) || "mipmap".equalsIgnoreCase(resType)) {
            String folder = MainActivity.sDAppTheme + File.separator + "img";
            File zipBackgroundPNG = new File(folder, resName + ".png");
            if (zipBackgroundPNG.exists()) {
                return true;
            }
            File zipBackgroundJPG = new File(folder, resName + ".jpg");
            if (zipBackgroundJPG.exists()) {
                return true;
            }
            File zipBackgroundGIF = new File(folder, resName + ".gif");
            if (zipBackgroundGIF.exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 动态获取该主题包颜色
     *
     * @param context
     * @param resId
     * @return
     */
    public ColorStateList getColor(Context context, int resId) {
        ColorStateList colorStateList = context.getResources().getColorStateList(resId);
        String resName = context.getResources().getResourceEntryName(resId);
        String resType = context.getResources().getResourceTypeName(resId);
        if ("color".equalsIgnoreCase(resType)) {
            String colorstr = ZipSDCardLoader.colors.get(resName);
            if (!TextUtils.isEmpty(colorstr)) {
                int color = Color.parseColor("#" + colorstr);
                StateListDrawable stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(new int[]{-android.R.attr.state_pressed}, new ColorDrawable(color));
                int[] colors = new int[]{color};
                int[][] states = new int[1][];
                states[0] = new int[]{};
                return new ColorStateList(states, colors);
            }
            return colorStateList;
        }
        return colorStateList;
    }
}
