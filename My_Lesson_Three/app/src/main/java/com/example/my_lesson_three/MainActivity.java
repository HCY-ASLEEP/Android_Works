package com.example.my_lesson_three;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BasicActivity {
    //主活动主要负责其他活动的跳转以及网络变化的广播接收器
    //监测网络变化的广播是由系统来发出的
    //接收到网络变化的广播接收器在主活动里面调用如下所示
    //广播接收器的intent-filter在安卓8.0之后都是动态注册的
    //学习一下这里的lambda表达式！

    private NetworkChangeReceiver networkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver=new NetworkChangeReceiver(this);
        registerReceiver(networkChangeReceiver, intentFilter);

        Button button=(Button) findViewById(R.id.main_button);
        button.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this, STD_BroadcastActivity.class);
            startActivity(intent);
        });

        Button button1=(Button) findViewById(R.id.main_button_2);
        button1.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this, OrderBroadcastActivity.class);
            startActivity(intent);
        });

        Button button2=(Button) findViewById(R.id.main_button_3);
        button2.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this, LocalBroadcastActivity.class);
            startActivity(intent);
        });

        Button button3=(Button) findViewById(R.id.forceOffLine);
        button3.setOnClickListener(view -> {
            Intent intent=new Intent("my.ForceOffLine");
            sendBroadcast(intent);
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }
}