package com.ximsfei.skindemo.alert;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ximsfei.skindemo.BaseActivity;
import com.ximsfei.skindemo.R;

public class AlertDialogActivity extends BaseActivity {
    private ListView mListView;
    private final String[] mItems = {"基础样式", "单选样式", "多选样式", "列表样式", "自定义样式"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        initToolbar();
        mListView = (ListView) findViewById(R.id.list);
        mListView.setCacheColorHint(Color.TRANSPARENT);
        mListView.setFadingEdgeLength(0);
        mListView.setAdapter(new AlertDialogAdapter());

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogActivity.this);
                switch (mItems[position]) {
                    case "基础样式":
                        builder.setTitle("标题")
                                .setIcon(R.drawable.ic_refresh_24dp)
                                .setMessage("简单消息框")
                                .setPositiveButton("是", null)
                                .setNegativeButton("否", null)
                                .setNeutralButton("取消", null);
                        break;
                    case "单选样式":
                        builder.setTitle("请选择")
                                .setIcon(R.drawable.ic_refresh_24dp)
                                .setSingleChoiceItems(new String[]{"选项1", "选项2", "选项3", "选项4"}, 0,
                                        new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        }
                                )
                                .setNegativeButton("取消", null);
                        break;
                    case "多选样式":
                        builder.setTitle("多选框")
                                .setIcon(R.drawable.ic_refresh_24dp)
                                .setMultiChoiceItems(new String[]{"选项1", "选项2", "选项3", "选项4"}, null, null)
                                .setPositiveButton("确定", null)
                                .setNegativeButton("取消", null);
                        break;
                    case "列表样式":
                        builder.setTitle("列表框")
                                .setItems(new String[]{"列表项1", "列表项2", "列表项3"}, null)
                                .setNegativeButton("确定", null);
                        break;
                    case "自定义样式":
                        builder.setTitle("请输入")
                                .setIcon(R.drawable.ic_refresh_24dp)
                                .setView(getLayoutInflater().inflate(R.layout.alert_dialog_content, null))
                                .setPositiveButton("确定", null)
                                .setNegativeButton("取消", null);
                        break;
                }
                builder.show();
            }
        });
    }

    private class AlertDialogAdapter extends BaseAdapter {
        private DisplayMetrics mDisplayMetrics;

        public AlertDialogAdapter() {
            mDisplayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        }

        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            int padding = (int) (mDisplayMetrics.density * 10);


            TextView tv = (TextView) getLayoutInflater().inflate(R.layout.simple_spinner_item, null);
            tv.setText(mItems[position]);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            tv.setTextAppearance(AlertDialogActivity.this, R.style.SkinCompatTextAppearance);
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(padding, padding, padding, padding);
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                    AbsListView.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(lp);
            return tv;
        }
    }
}
