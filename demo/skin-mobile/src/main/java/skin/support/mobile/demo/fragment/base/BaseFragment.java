package skin.support.mobile.demo.fragment.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import skin.support.mobile.demo.R;

public abstract class BaseFragment extends Fragment {
    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_base, container, false);

        LinearLayout containerLl = root.findViewById(R.id.ll_container);

        int layoutId = getLayoutId();
        View content = inflater.inflate(layoutId, null);
        containerLl.addView(content);

        return root;
    }
}
