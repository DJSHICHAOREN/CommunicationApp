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
import com.example.communicationapp.database.MyDatabaseHelper;
import com.example.communicationapp.database.PositionData;
import com.example.communicationapp.entity.Position;
import com.example.communicationapp.entity.SubmitPositionParam;
import com.example.communicationapp.http.PositionService;
import com.example.communicationapp.util.DeviceUtil;
import com.example.communicationapp.util.HttpServiceCreator;
import com.example.communicationapp.util.LocationUtil;
import com.example.communicationapp.view.MainActivity;
import com.example.communicationapp.R;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPositionService extends Service {
    final PositionService positionService = HttpServiceCreator.create(PositionService.class);


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        LocationUtil.getInstance().setLocationListener(new AMapLocationListener(){

            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                Log.d("lwd", "enter onLocationChanged");
                if (aMapLocation != null) {

                    if (aMapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。
                        double latitude = aMapLocation.getLatitude();//获取纬度
                        double longitude = aMapLocation.getLongitude();//获取经度
                        String street = aMapLocation.getStreet();
                        Log.d("lwd", "获取数据成功 latitude:" + latitude
                                + " longitude:" + longitude
                                + " street:" + street);

                    }else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("lwd","location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
//                    sendRequest(new Position(aMapLocation));

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            PositionData positionData = new PositionData(aMapLocation);
                            long insertedId = MyDatabaseHelper.getInstance(GetPositionService.this).getPositionDao().insert(positionData);
                            Log.d("lwd", "insertedId:" + insertedId);
                        }
                    }).start();

                }

            }
        });
        LocationUtil.getInstance().startLocation();

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                // 在这里编写定时执行的任务逻辑
//                Log.d("lwd", "Timer print " + System.currentTimeMillis());
//                LocationUtil.getInstance().startLocation();
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                LocationUtil.getInstance().stopLocation();
//            }
//        }, 0, 1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        createNotification();

        return super.onStartCommand(intent, flags, startId);
    }

    // 创建前台服务的常驻通知
    public void createNotification(){

        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channel = null;
        Intent intent1 = new Intent(this, MainActivity.class);
        PendingIntent intent2 = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_MUTABLE);

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
    }

    public void sendRequest(Position position){

        SubmitPositionParam submitPositionParam = new SubmitPositionParam();
        submitPositionParam.setDevice(DeviceUtil.getDevice(this));
        submitPositionParam.setPosition(position);

        positionService.submitPosition(submitPositionParam).enqueue(new Callback<Position>() {
            @Override
            public void onResponse(Call<Position> call, Response<Position> response) {
                Position position = response.body();
                if(position != null){
                    Log.d("lwd", "发送数据成功 latitude：" + position.getLatitude() + " longitude:" + position.getLongitude());
                }
            }

            @Override
            public void onFailure(Call<Position> call, Throwable t) {
                Log.d("lwd", t.getMessage());
            }
        });
    }

}
