package com.afei.skinzipdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dadafei.skin_zip_demo.R;
import com.afei.skinzipdemo.skin.ZipSDCardLoader;
import com.afei.skinzipdemo.utils.FileUtils;
import com.afei.skinzipdemo.utils.ZipUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

import skin.support.SkinCompatManager;

public class MainActivity extends AppCompatActivity {

    //路径
    public static final String defaultzip = "default.zip";
    public static final String appTheme = App.app
            .getFilesDir()
            .toString() +
            File.separator + "DWAppTheme";
    public static final String sDzip = appTheme + File.separator + defaultzip;
    public static final String sDAppTheme = appTheme + File.separator + "default";
    public static final String colors = sDAppTheme + File.separator + "colors.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadData();
    }

    /**
     * 初始化控件
     */
    private void init() {

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "切换资源", Toast.LENGTH_LONG).show();
                //切换资源
                SkinCompatManager.getInstance()
                        .loadSkin("", ZipSDCardLoader.SKIN_LOADER_STRATEGY_ZIP);
            }
        });
    }

    /**
     * 将ass的zip主题包解压到APP私有路径下
     * 将里面的colors.json 解析出来
     */
    private void loadData() {

        File appThemeFile = new File(appTheme);
        if (!appThemeFile.exists()) {
            appThemeFile.mkdirs();
        }

        copyFilesFassets(this, defaultzip, sDzip);
        File sDAppThemeFile = new File(sDAppTheme);

        if (sDAppThemeFile.exists()) {
            FileUtils.deleteFiles(sDAppThemeFile);
        }

        try {
            ZipUtils.UnZipFolder(sDzip, appTheme);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!sDAppThemeFile.exists()) {
            Toast.makeText(this, "解压数据包失败", Toast.LENGTH_LONG).show();
        }

        File colorsFile = new File(colors);
        if (colorsFile.exists()) {
            String json = resolveFileGetStr(colorsFile);
            Map<String, String> color = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {
            }.getType());
            if (color != null && color.size() > 0) ZipSDCardLoader.colors.putAll(color);
        }
    }

    /**
     * 将APP自带的bar默认主题包从ASS路径解压出来
     *
     * @param context
     * @param oldPath
     * @param newPath
     */
    public static void copyFilesFassets(Context context, String oldPath, String newPath) {
        try {

            String fileNames[] = context.getAssets().list(oldPath);//获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {//如果是目录
                File file = new File(newPath);
                file.mkdirs();//如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesFassets(context, oldPath + "/" + fileName, newPath + "/" + fileName);
                }
            } else {//如果是文件
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {//循环从输入流读取 buffer字节
                    fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                }
                fos.flush();//刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件内容转为string对象
     *
     * @param file
     * @return
     */
    private static String resolveFileGetStr(File file) {
        int length = (int) file.length();

        byte[] bytes = new byte[length];

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            in.read(bytes);
            in.close();
        } catch (Exception e) {
            return null;
        }
        String contents = new String(bytes);
        return contents;
    }
}
