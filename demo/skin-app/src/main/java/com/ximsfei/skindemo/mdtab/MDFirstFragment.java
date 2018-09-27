package com.ximsfei.skindemo.mdtab;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ximsfei.skindemo.R;

/**
 * Created by ximsfei on 17-3-2.
 */

public class MDFirstFragment extends Fragment {
    private View mView;
    private EditText editText;
    private TextInputLayout textInputLayout;
    private EditText editText2;
    private TextInputLayout textInputLayout2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_md_first, null);
        initTextInputLayout();
        initTextInputLayout2();
        mView.findViewById(R.id.CollapsingToolbarLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CollapsingToolbarLayoutActivity.class));
            }
        });
        return mView;
    }

    private void initTextInputLayout2() {
        textInputLayout2 = (TextInputLayout) mView.findViewById(R.id.textInputLayout2);
        textInputLayout2.setHint("请输入4位学号");
        editText2 = (EditText) mView.findViewById(R.id.editText2);
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 4) {
                    editText2.setError("学号输入错误");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initTextInputLayout() {
        textInputLayout = (TextInputLayout) mView.findViewById(R.id.textInputLayout);
        textInputLayout.setHint("请输入4位学号");
        editText = (EditText) mView.findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 4) {
                    textInputLayout.setError("学号输入错误");
                    textInputLayout.setErrorEnabled(true);
                } else {
                    textInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
