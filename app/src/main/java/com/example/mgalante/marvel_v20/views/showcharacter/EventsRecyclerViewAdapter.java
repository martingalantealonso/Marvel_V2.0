package com.example.mgalante.marvel_v20.views.showcharacter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.mgalante.marvel_v20.R;
import com.example.mgalante.marvel_v20.api.entities.Event;
import com.example.mgalante.marvel_v20.control.adapters.CommonViewHolder;

import java.util.List;


public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<CommonViewHolder<Event>> {

    private final Context mContext;
    private List<Event> mValues;

    public EventsRecyclerViewAdapter(Context context, List<Event> items) {
        mContext = context;
        mValues = items;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_resource, parent, false);
        return new CommonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.name.setText(mValues.get(position).getTitle());

        holder.subname.setText(mValues.get(position).getDescription());

        String urlImage = mValues.get(position).getThumbnail().getPath() + "." + mValues.get(position).getThumbnail().getExtension();
        Glide.with(mContext).load(urlImage).into(holder.avatar);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void fillData(List<Event> list) {
        mValues = list;
    }
}
