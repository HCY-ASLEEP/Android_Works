package com.example.my_lesson_three;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class LocalBroadcastActivity extends BasicActivity {
    private LocalBroadcastManager localBroadcastManager;
    private STD_BroadcastReceiver receiver1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);

        Button button= findViewById(R.id.forth_button);
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
        receiver1 =new STD_BroadcastReceiver();
        String action="local";
        IntentFilter intentFilter=new IntentFilter(action);
        localBroadcastManager.registerReceiver(receiver1,intentFilter);

        button.setOnClickListener(view -> {
            Intent intent=new Intent();
            intent.setAction("local");
            localBroadcastManager.sendBroadcast(intent);
        });
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(receiver1);
    }
}