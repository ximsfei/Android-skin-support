package com.ximsfei.skindemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ximsfei.skindemo.R;

public class ComponentsFragment extends Fragment {
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_components, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ItemAdapter adapter = new ItemAdapter();
//        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
//        adapter.setItems(new ArrayList());
        mRecyclerView = view.findViewById(R.id.recycler_view);
//        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }
//
//    private static class ItemAdapter extends BaseRecyclerAdapter {
//
//        @Override
//        protected int getItemLayoutId(int viewType) {
//            return R.layout.item_main_layout;
//        }
//
//        @Override
//        protected void bindData(ViewHolder holder, int position, Object item) {
//
//        }
//    }
}
