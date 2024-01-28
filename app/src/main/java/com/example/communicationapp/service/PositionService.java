package com.example.communicationapp.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.example.communicationapp.User;
import com.example.communicationapp.UserService;
import com.example.communicationapp.util.HttpServiceCreator;
import com.example.communicationapp.util.LocationUtil;
import com.example.communicationapp.R;
import com.example.communicationapp.view.MainActivity;

import java.util.List;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PositionService extends Service {

    private LocationUtil locationUtil;

    public PositionService(){
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final UserService userService = HttpServiceCreator.create(UserService.class);

        locationUtil = new LocationUtil(getApplicationContext());
        locationUtil.setLocationListener(new AMapLocationListener(){

            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {

                userService.getUsers().enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        List<User> userList = response.body();
                        if(userList != null){
                            for(User user : userList){
                                Log.d("lwd", "name:" + user.name);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Log.d("lwd", t.getMessage());
                    }
                });

            }
        });
        locationUtil.startLocation();


        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channel = null;
        Intent intent1 = new Intent(this, MainActivity.class);
        PendingIntent intent2 = PendingIntent.getActivity(this, 0, intent1,0);


        Notification.Builder builder = new Notification.Builder(this);
        //Android8.0要求设置通知渠道
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            String channelId = "foreground";
            channel = new NotificationChannel(channelId, "foregroundName", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);

            builder.setChannelId(channelId);
        }
        else{
            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("前台服务")
                    .setContentText("播放器用的是前台服务")
                    .setContentIntent(intent2)
                    .build();
        }

        Notification notification = builder.build();
        startForeground(123, notification);

        return super.onStartCommand(intent, flags, startId);
    }

}
