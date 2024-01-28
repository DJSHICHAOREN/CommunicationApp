package com.example.communicationapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communicationapp.R;
import com.example.communicationapp.entity.Position;
import com.example.communicationapp.view.vh.LocationItemViewHolder;

import java.util.List;

public class LocationListAdapter extends RecyclerView.Adapter<LocationItemViewHolder> {
    List<Position> mPositionList;
    public LocationListAdapter(List<Position> positionList) {
        mPositionList = positionList;
    }
    @NonNull
    @Override
    public LocationItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LocationItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.viewholder_location_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationItemViewHolder holder, int position) {
        holder.bind(mPositionList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPositionList.size();
    }
}
