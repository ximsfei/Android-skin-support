package com.ximsfei.skindemo.picker;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T, V extends BaseRecyclerAdapter.BaseViewHolder> extends RecyclerView.Adapter<V> {
    private List<T> mItems = new ArrayList<>();
    private AdapterView.OnItemClickListener mOnItemClickListener;
    private AdapterView.OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    protected void onItemHolderClick(V itemHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }

    protected void onItemHolderLongClick(V itemHolder) {
        if (mOnItemLongClickListener != null) {
            mOnItemLongClickListener.onItemLongClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public T getItem(int pos) {
        return mItems.get(pos);
    }

    public void setItems(List<T> items) {
        if (items != null) {
            mItems.clear();
            mItems.addAll(items);
            notifyDataSetChanged();
        }
    }

    public void addItem(int pos, T item) {
        mItems.add(pos, item);
        notifyItemInserted(pos);
    }

    public void removeItem(int pos) {
        mItems.remove(pos);
        notifyItemRemoved(pos);
    }

    public static class BaseViewHolder<T extends BaseRecyclerAdapter> extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        protected final T mAdapter;

        public BaseViewHolder(View itemView, final T adapter) {
            super(itemView);
            mAdapter = adapter;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mAdapter.onItemHolderClick(this);
        }

        @Override
        public boolean onLongClick(View v) {
            mAdapter.onItemHolderLongClick(this);
            return true;
        }
    }
}
