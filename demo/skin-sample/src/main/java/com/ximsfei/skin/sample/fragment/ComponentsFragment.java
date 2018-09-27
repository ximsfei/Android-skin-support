package com.ximsfei.skin.sample.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ximsfei.skin.sample.R;
import com.ximsfei.skin.sample.activities.components.ComponentActivity;
import com.ximsfei.skin.sample.activities.components.TextViewActivity;
import com.ximsfei.skin.sample.base.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

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
        final ComponentsAdapter adapter = new ComponentsAdapter();
        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ComponentsData data = adapter.getItem(position);
                Intent intent = new Intent(getActivity(), data.activityClass);
                intent.putExtra(ComponentActivity.KEY_TITLE, data.name);
                startActivity(intent);
            }
        });
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                ComponentsData data = adapter.getItem(position);
                if (data.type == ComponentsAdapter.TYPE_HEADER) {
                    return 1;
                }
                return 3;
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);
        adapter.setItems(prepareData());
    }

    private List<ComponentsData> prepareData() {
        List<ComponentsData> dataList = new ArrayList<>();
        dataList.add(generateItem("Basic", R.drawable.icon_text, null, ComponentsAdapter.TYPE_HEADER));
        dataList.add(generateItem("Text", R.drawable.icon_text, TextViewActivity.class, ComponentsAdapter.TYPE_COMPONENT));
        dataList.add(generateItem("Button", R.drawable.icon_text, TextViewActivity.class, ComponentsAdapter.TYPE_COMPONENT));
        dataList.add(generateItem("Image", R.drawable.icon_image, TextViewActivity.class, ComponentsAdapter.TYPE_COMPONENT));
        dataList.add(generateItem("Progress", R.drawable.icon_progress, TextViewActivity.class, ComponentsAdapter.TYPE_COMPONENT));
        dataList.add(generateItem("Material design", R.drawable.icon_text, null, ComponentsAdapter.TYPE_HEADER));
        dataList.add(generateItem("Material Design", R.drawable.icon_text, TextViewActivity.class, ComponentsAdapter.TYPE_COMPONENT));
        dataList.add(generateItem("CardView", R.drawable.icon_text, TextViewActivity.class, ComponentsAdapter.TYPE_COMPONENT));
        dataList.add(generateItem("ConstraintLayout", R.drawable.icon_text, TextViewActivity.class, ComponentsAdapter.TYPE_COMPONENT));
        return dataList;
    }

    private ComponentsData generateItem(String name, @DrawableRes int icon,
                                        Class<?> activityClass, int type) {
        ComponentsData data = new ComponentsData();
        data.name = name;
        data.icon = icon;
        data.activityClass = activityClass;
        data.type = type;
        return data;
    }

    private static class ComponentsData {
        String name;
        @DrawableRes
        int icon;
        Class<?> activityClass;
        int type;
    }

    private class ComponentsAdapter extends BaseRecyclerAdapter<ComponentsData, ComponentsViewHolder> {
        private static final int TYPE_HEADER = 0;
        private static final int TYPE_COMPONENT = 1;

        @Override
        public int getItemViewType(int position) {
            return getItem(position).type;
        }

        @NonNull
        @Override
        public ComponentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == TYPE_HEADER) {
                View view = getLayoutInflater().inflate(R.layout.item_header_layout, parent, false);
                return new ComponentsViewHolder(view, this);
            } else {
                View view = getLayoutInflater().inflate(R.layout.item_components_layout, parent, false);
                return new ComponentsViewHolder(view, this);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull ComponentsViewHolder holder, int position) {
            ComponentsData data = getItem(position);
            holder.mIcon.setImageResource(data.icon);
            holder.mName.setText(data.name);
        }
    }

    private static class ComponentsViewHolder extends BaseRecyclerAdapter.BaseViewHolder<ComponentsAdapter> {
        private final ImageView mIcon;
        private final TextView mName;

        public ComponentsViewHolder(View itemView, ComponentsAdapter adapter) {
            super(itemView, adapter);
            mIcon = itemView.findViewById(R.id.icon);
            mName = itemView.findViewById(R.id.name);
        }
    }
}
