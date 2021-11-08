package com.example.my_lesson_three;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

public class BasicActivity extends AppCompatActivity {
    //在系统提供的基础活动类上面加入了自己的活动管理
    private ForceOffLineReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        ActivityAdministrator.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityAdministrator.removeActivity(this);
    }

    //为每一个活动都注册一个强制下线的广播接收器
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("my.ForceOffLine");
        receiver = new ForceOffLineReceiver();
        registerReceiver(receiver, intentFilter);
    }

    //要记得取消注册广播接收器
    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }
}