package com.afei.skinzipdemo.skin;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.Log;


import com.afei.skinzipdemo.MainActivity;
import com.afei.skinzipdemo.utils.SkinTool;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import skin.support.load.SkinSDCardLoader;
import skin.support.utils.SkinFileUtils;

public class ZipSDCardLoader extends SkinSDCardLoader {
    public static final boolean DEBUG = false;
    public static final String DEBUG_TAG = "ZipSDCardLoader";
    public static final int SKIN_LOADER_STRATEGY_ZIP = Integer.MAX_VALUE - 1;
    public static float wMultiple, hMultiple;

    //简单的缓存每次读到的图片
    public static Map<Integer, Drawable> imgs = new HashMap<>();

    public static Map<String, String> colors = new HashMap<>();

    @Override
    public String loadSkinInBackground(Context context, String skinName) {
        return super.loadSkinInBackground(context, skinName);
    }

    @Override
    protected String getSkinPath(Context context, String skinName) {
        return new File(SkinFileUtils.getSkinDir(context), skinName).getAbsolutePath();
    }

    @Override
    public Drawable getDrawable(Context context, String skinName, int resId) {
        String resName = context.getResources().getResourceEntryName(resId);
        String resType = context.getResources().getResourceTypeName(resId);
        if (DEBUG) Log.e(DEBUG_TAG, "类型   名称： " + resType + "   名称： " + resName);
        if ("drawable".equalsIgnoreCase(resType) || "mipmap".equalsIgnoreCase(resType)) {
            String folder = MainActivity.sDAppTheme + File.separator + "img";
            Drawable drawable = get(resId);
            if (drawable != null) {
                return drawable;
            }
            File zipBackgroundPNG = new File(folder, resName + ".png");
            if (zipBackgroundPNG.exists()) {
                drawable = loadMultiple(context,
                        resId,
                        Drawable.createFromPath(zipBackgroundPNG.getAbsolutePath()));
            }
            File zipBackgroundJPG = new File(folder, resName + ".jpg");
            if (zipBackgroundJPG.exists()) {
                drawable = loadMultiple(context,
                        resId,
                        Drawable.createFromPath(zipBackgroundJPG.getAbsolutePath()));

            }
            /**
             * 这里是为了解决引用了一个gif的库
             */
//            File zipBackgroundGIF = new File(dir, resName + ".gif");
//            if (zipBackgroundGIF.exists()) {
//                try {
//                    drawable = new GifDrawable(zipBackgroundGIF.getAbsolutePath());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    drawable = loadMultiple(context,
//                            resId,
//                            Drawable.createFromPath(zipBackgroundGIF.getAbsolutePath()));
//                }
//            }
            add(resId, drawable);
            if (DEBUG && drawable == null) Log.e(DEBUG_TAG, "未找到 图片   名称： " + resName);
            if (DEBUG && drawable != null)
                Log.e(DEBUG_TAG, "名称： " + resName + " 宽" + drawable.getIntrinsicHeight() + " 高" + drawable.getIntrinsicWidth());
            return drawable;
        }
        if ("color".equalsIgnoreCase(resType)) {
            String colorstr = colors.get(resName);
            if (!TextUtils.isEmpty(colorstr)) {
                int color = Color.parseColor("#" + colorstr);
                if (DEBUG) Log.e(DEBUG_TAG, "跟换颜色   名称： " + resName + "颜色更换为 ：" + colorstr);
                StateListDrawable stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(new int[]{-android.R.attr.state_pressed}, new ColorDrawable(color));
                return stateListDrawable;
            } else {
                if (DEBUG) Log.e(DEBUG_TAG, "未找到 颜色   名称： " + resName);
            }
            return null;
        }
        return super.getDrawable(context, skinName, resId);
    }


    @Override
    public ColorStateList getColorStateList(Context context, String skinName, int resId) {
        String resName = context.getResources().getResourceEntryName(resId);
        String resType = context.getResources().getResourceTypeName(resId);
        if (DEBUG) Log.e(DEBUG_TAG, "类型   名称： " + resType + "  名字   名称： " + resName);
        if ("color".equalsIgnoreCase(resType)) {
            String colorstr = colors.get(resName);
            if (!TextUtils.isEmpty(colorstr)) {
                int color = Color.parseColor("#" + colorstr);
                if (DEBUG) Log.e(DEBUG_TAG, "跟换颜色   名称： " + resName + "颜色更换为 ：" + colorstr);
                StateListDrawable stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(new int[]{-android.R.attr.state_pressed}, new ColorDrawable(color));
                int[] colors = new int[]{color};
                int[][] states = new int[1][];
                states[0] = new int[]{};
                return new ColorStateList(states, colors);
            } else {
                if (DEBUG) Log.e(DEBUG_TAG, "未找到 颜色   名称： " + resName);
            }
            return null;
        }
        return super.getColorStateList(context, skinName, resId);
    }

    @Override
    public ColorStateList getColor(Context context, String skinName, int resId) {
        String resName = context.getResources().getResourceEntryName(resId);
        String resType = context.getResources().getResourceTypeName(resId);
        if (DEBUG) Log.e(DEBUG_TAG, "类型   名称： " + resType + "  名字   名称： " + resName);
        if ("color".equalsIgnoreCase(resType)) {
            String colorstr = colors.get(resName);
            if (!TextUtils.isEmpty(colorstr)) {
                int color = Color.parseColor("#" + colorstr);
                if (DEBUG) Log.e(DEBUG_TAG, "跟换颜色   名称： " + resName + "颜色更换为 ：" + colorstr);
                StateListDrawable stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(new int[]{-android.R.attr.state_pressed}, new ColorDrawable(color));
                int[] colors = new int[]{color};
                int[][] states = new int[1][];
                states[0] = new int[]{};
                return new ColorStateList(states, colors);
            } else {
                if (DEBUG) Log.e(DEBUG_TAG, "找不到的   名称： " + resType + "   名称： " + resName);
            }
            return null;
        }
        return super.getColor(context, skinName, resId);
    }

    @Override
    public int getType() {
        return SKIN_LOADER_STRATEGY_ZIP;
    }

    /**
     * 解决系统缩小图片
     * 如果你之前的图片是存放在xxhpai文件下的话  那么你文件读到的图片 会比自带的图片 小2-3倍（根据系统而定）
     * 建议APP自带的图片都存在xxhpai下  可以直接使用本函数解决  如果有更好的办法就不要用该函数
     * @param context
     * @param resId
     * @param zipDrawable
     * @return
     */
    public static Drawable loadMultiple(Context context, int resId, Drawable zipDrawable) {
        Drawable nowDrawable = null;
        if (wMultiple == 0 || hMultiple == 0) {
            Drawable oldDrawable = context.getResources().getDrawable(resId);
            wMultiple = oldDrawable.getIntrinsicWidth() / zipDrawable.getIntrinsicWidth();
            hMultiple = oldDrawable.getIntrinsicHeight() / zipDrawable.getIntrinsicHeight();

        }
        int newWMultiple, newHMultiple;
        newWMultiple = wMultiple == 2 ? 8 : 2;
        newWMultiple = wMultiple == 3 ? 9 : newWMultiple;

        newHMultiple = hMultiple == 2 ? 8 : 3;
        newHMultiple = hMultiple == 3 ? 9 : newHMultiple;

        int w = zipDrawable.getIntrinsicWidth();
        int h = zipDrawable.getIntrinsicHeight();

        nowDrawable = SkinTool
                .init()
                .zoomDrawable(zipDrawable,
                        (int) Math.ceil(w * (newWMultiple)),
                        (int) Math.ceil(h * (newHMultiple)));
        return nowDrawable;
    }

    public static void add(int rId, Drawable drawable) {
        if (imgs == null) {
            imgs = new HashMap<>();
        }
        imgs.put(rId, drawable);
    }

    public static Drawable get(int rId) {
        if (imgs == null) {
            imgs = new HashMap<>();
        }
        return imgs.get(rId);
    }

    public static void claer() {
        if (imgs == null) {
            imgs = new HashMap<>();
        }
        imgs.clear();
    }
}
