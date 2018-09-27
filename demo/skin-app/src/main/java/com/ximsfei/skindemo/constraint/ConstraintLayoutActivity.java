package com.ximsfei.skindemo.constraint;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.ximsfei.skindemo.BaseActivity;
import com.ximsfei.skindemo.R;
import com.ximsfei.skindemo.settings.SettingsActivity;

/**
 * Created by pengfengwang on 2017/6/19.
 */

public class ConstraintLayoutActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);

        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConstraintLayoutActivity.this, SettingsActivity.class));
            }
        });
    }
}
