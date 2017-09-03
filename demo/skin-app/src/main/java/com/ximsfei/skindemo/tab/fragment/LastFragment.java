package com.ximsfei.skindemo.tab.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ximsfei.skindemo.R;

/**
 * Created by ximsfei on 17-1-14.
 */

public class LastFragment extends Fragment {
    private EditText mEdit;
    private EditText mEdit1;
    private TextView mText1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);
        mEdit = (EditText) view.findViewById(R.id.edit);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mEdit.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.drawable_left_selector,
                    R.drawable.drawable_top_selector,
                    R.drawable.drawable_right_selector,
                    R.drawable.drawable_bottom_selector);
        }
        mEdit1 = (EditText) view.findViewById(R.id.edit1);
        mEdit1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.drawable_left_selector,
                R.drawable.drawable_top_selector,
                R.drawable.drawable_right_selector,
                R.drawable.drawable_bottom_selector);
        mText1 = (TextView) view.findViewById(R.id.text1);
        mText1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.drawable_left_selector),
                getResources().getDrawable(R.drawable.drawable_top_selector),
                getResources().getDrawable(R.drawable.drawable_right_selector),
                getResources().getDrawable(R.drawable.drawable_bottom_selector));
        return view;
    }
}
