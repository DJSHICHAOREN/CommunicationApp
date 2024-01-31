package com.example.communicationapp.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communicationapp.R;
import com.example.communicationapp.database.MyDatabaseHelper;
import com.example.communicationapp.database.PositionData;
import com.example.communicationapp.entity.Position;
import com.example.communicationapp.view.adapter.LocationListAdapter;

import java.util.List;

public class LocationFragment extends Fragment {
    private RecyclerView rv_location;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container,false);
        rv_location = view.findViewById(R.id.rv_location);
        rv_location.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        setData();
    }

    private void setData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<PositionData> positionDataList = MyDatabaseHelper.getInstance(getContext()).getPositionDao().selectAll();
                rv_location.post(new Runnable() {
                    @Override
                    public void run() {
                        LocationListAdapter locationListAdapter = new LocationListAdapter(Position.parse(positionDataList));
                        rv_location.setAdapter(locationListAdapter);
                    }
                });

            }
        }).start();

    }

    public static LocationFragment newInstance(Bundle args) {
        LocationFragment locationFragment = new LocationFragment();
        locationFragment.setArguments(args);
        return locationFragment;
    }
}
