package com.ximsfei.skindemo.tab.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ximsfei.skindemo.R;

/**
 * Created by ximsfei on 17-1-14.
 */

public class TFragment extends Fragment {
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplication()));
        mRecyclerView.addItemDecoration(new CustomDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new TAdapter());
        return view;
    }

    private class TAdapter extends RecyclerView.Adapter<TAdapter.TViewHolder> {
        @Override
        public TViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TViewHolder(
                    LayoutInflater.from(getActivity())
                            .inflate(R.layout.item_t, parent, false));
        }

        @Override
        public void onBindViewHolder(TViewHolder holder, int position) {
            ((TextView) holder.itemView).setText(String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            return 6;
        }

        class TViewHolder extends RecyclerView.ViewHolder {

            public TViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
