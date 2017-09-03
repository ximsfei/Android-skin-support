package com.ximsfei.skindemo.tab.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.ximsfei.skindemo.R;

/**
 * Created by ximsfei on 17-1-14.
 */

public class FirstFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, null);
        view.findViewById(R.id.image_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Image Button", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.checked_text_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                CheckedTextView checkedTextView = (CheckedTextView) v;
                checkedTextView.toggle();
//                checkedMap.put(position, checkedTextView.isChecked());
            }
        });
        MultiAutoCompleteTextView autoCompleteTextView = (MultiAutoCompleteTextView) view.findViewById(R.id.auto);
        String[] arr = {"aa", "aab", "aac"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arr);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        return view;
    }
}
