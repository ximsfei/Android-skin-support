package com.ximsfei.skindemo.tab.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.ximsfei.skindemo.R;

/**
 * Created by ximsfei on 17-1-14.
 */

public class SFragment extends Fragment {
    private ProgressBar mHorizontalBar;
    private Button mAdd;
    private Spinner mSpinner;

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
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
        final CharSequence[] entries = getResources().getStringArray(R.array.languages);
        if (entries != null) {
            final ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(getActivity(), R.layout.simple_spinner_item, entries);
            adapter.setDropDownViewResource(R.layout.simple_spinner_item);
            mSpinner.setAdapter(adapter);
        }
        return view;
    }
}
