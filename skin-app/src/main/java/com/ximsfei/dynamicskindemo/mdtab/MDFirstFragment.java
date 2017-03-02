package com.ximsfei.dynamicskindemo.mdtab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ximsfei.dynamicskindemo.R;

/**
 * Created by ximsfei on 17-3-2.
 */

public class MDFirstFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_md_first, null);
        return view;
    }
}
