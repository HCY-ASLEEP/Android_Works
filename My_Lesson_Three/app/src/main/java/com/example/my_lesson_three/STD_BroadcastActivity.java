package com.example.my_lesson_three;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class STD_BroadcastActivity extends BasicActivity {
    //这是另外一种发送广播的方式
    //这一种方式通过指定广播接收器的包名以及类名来找到广播接收器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button button= findViewById(R.id.sendBroadCastButton);
        button.setOnClickListener(view -> {
            Intent intent=new Intent();
            intent.setComponent(new ComponentName("com.example.my_lesson_three","com.example.my_lesson_three.STD_BroadcastReceiver"));
            sendBroadcast(intent);
        });
    }
}