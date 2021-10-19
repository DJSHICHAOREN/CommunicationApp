package com.example.communicationapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.communicationapp.view.MainActivity;

public class AutoStartBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent autoStartIntent = new Intent(context, MainActivity.class);
        autoStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(autoStartIntent);
    }
}
