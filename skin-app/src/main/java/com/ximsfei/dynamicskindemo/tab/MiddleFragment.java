package com.ximsfei.dynamicskindemo.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.ximsfei.dynamicskindemo.R;

/**
 * Created by ximsfei on 17-1-14.
 */

public class MiddleFragment extends Fragment {
    private ProgressBar mHorizontalBar;
    private Button mAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_middle, null);
        mHorizontalBar = (ProgressBar) view.findViewById(R.id.progress_horizontal);
        mAdd = (Button) view.findViewById(R.id.add);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHorizontalBar.setProgress(mHorizontalBar.getProgress() + 2);
            }
        });
        return view;
    }
}
