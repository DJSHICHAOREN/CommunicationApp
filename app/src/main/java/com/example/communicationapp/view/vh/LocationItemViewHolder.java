package com.example.communicationapp.view.vh;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communicationapp.R;
import com.example.communicationapp.entity.Position;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LocationItemViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_time;
    private TextView tv_latitude;
    private TextView tv_longitude;

    public LocationItemViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_time = itemView.findViewById(R.id.tv_time);
        tv_latitude = itemView.findViewById(R.id.tv_latitude);
        tv_longitude = itemView.findViewById(R.id.tv_longitude);
    }

    public void bind(Position position) {
        Date date = new Date(position.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = format.format(date);
        tv_time.setText(timeStr);

        tv_latitude.setText(position.getLatitude() + "");
        tv_longitude.setText(position.getLongitude() + "");


    }
}
