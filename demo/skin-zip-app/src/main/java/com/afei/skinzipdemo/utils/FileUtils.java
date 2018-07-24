package com.afei.skinzipdemo.utils;

import java.io.File;

/**
 * Created by mac on 2018/5/8.
 */

public class FileUtils {

    public static void deleteFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

    public static void deleteFiles(File file) {
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files == null) {
                file.delete();
                return;
            }
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    File photoFile = new File(files[i].getPath());
                    photoFile.delete();
                }
            }
        }
    }
}
