package com.example.communicationapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communicationapp.R;
import com.example.communicationapp.entity.Position;
import com.example.communicationapp.view.adapter.LocationListAdapter;

import java.util.ArrayList;
import java.util.List;

public class LocationFragment extends Fragment {
    private RecyclerView rv_location;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container,false);
        rv_location = view.findViewById(R.id.rv_location);
        rv_location.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        setData();
        return view;
    }
    private void setData() {
        List<Position> positionList = new ArrayList<>();
        LocationListAdapter locationListAdapter = new LocationListAdapter(positionList);
        rv_location.setAdapter(locationListAdapter);
    }
}
