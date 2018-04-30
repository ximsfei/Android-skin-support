package com.ximsfei.skindemo.picker;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ximsfei.skindemo.BaseActivity;
import com.ximsfei.skindemo.R;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import skin.support.SkinCompatManager;
import skin.support.content.res.SkinCompatUserThemeManager;

public class DrawablePickerActivity extends BaseActivity {
    private Toolbar mToolbar;
    private DrawablePickerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_picker);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Drawable Picker");
        mToolbar.setSubtitle("Define your exclusive application.");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new DrawablePickerAdapter();
        mAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SkinCompatUserThemeManager.get().addDrawablePath(R.drawable.windowBackground, mAdapter.getItem(position));
                SkinCompatManager.getInstance().notifyUpdateSkin();
            }
        });
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(DrawablePickerActivity.this, 3));
        Button clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinCompatUserThemeManager.get().clearDrawables();
            }
        });
        Button apply = findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinCompatUserThemeManager.get().apply();
            }
        });
        if (ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE")
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1000);
        } else {
            loadData();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadData();
            }
        }
    }

    private void loadData() {
        new ResolvePicTask(mAdapter).execute();
    }

    private static class ResolvePicTask extends AsyncTask<Void, Void, List<String>> {
        private final DrawablePickerAdapter mAdapter;

        public ResolvePicTask(DrawablePickerAdapter adapter) {
            mAdapter = adapter;
        }

        @Override
        protected List<String> doInBackground(Void... voids) {
            String cameraDirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "DCIM" + File.separator + "Camera";
            File[] files = new File(cameraDirPath).listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().endsWith(".jpg") || pathname.getName().endsWith(".png");
                }
            });
            List<String> list = new ArrayList<>();
            for (File file : files) {
                list.add(file.getAbsolutePath());
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<String> pathList) {
            super.onPostExecute(pathList);
            if (pathList != null && pathList.size() > 0) {
                mAdapter.setItems(pathList);
            }
        }
    }

    private class DrawablePickerAdapter extends BaseRecyclerAdapter<String, DrawablePickerViewHolder> {

        @NonNull
        @Override
        public DrawablePickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_drawable_picker_layout, parent, false);
            return new DrawablePickerViewHolder(view, this);
        }

        @Override
        public void onBindViewHolder(@NonNull DrawablePickerViewHolder holder, int position) {
            Glide.with(DrawablePickerActivity.this).load(getItem(position)).into(holder.mImage);
        }
    }

    private static class DrawablePickerViewHolder extends BaseRecyclerAdapter.BaseViewHolder<DrawablePickerAdapter> {
        private final ImageView mImage;

        public DrawablePickerViewHolder(View itemView, DrawablePickerAdapter adapter) {
            super(itemView, adapter);
            mImage = (ImageView) itemView;
        }
    }
}
