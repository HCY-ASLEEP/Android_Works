package com.example.my_lesson_three;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class STD_AnotherBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"this is another receiver of the broadcast",Toast.LENGTH_SHORT).show();
        //abortBroadcast();
    }
}