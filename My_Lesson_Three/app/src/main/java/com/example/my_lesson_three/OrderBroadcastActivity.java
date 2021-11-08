package com.example.my_lesson_three;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;

public class OrderBroadcastActivity extends BasicActivity {
    //自从安卓8.0之后广播都不能通过静态来设置了，只能通过动态来注册广播
    //动态注册广播也就是通过动态创建intentFilter来实行广播的过滤
    //动态的设置优先级
    //动态创建的广播一定要最后在onDestroy方法最后取消注册广播

    private STD_BroadcastReceiver receiver1;
    private STD_AnotherBroadcastReceiver receiver2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_acitvity);

        receiver1=new STD_BroadcastReceiver();
        receiver2=new STD_AnotherBroadcastReceiver();
        String action="hcy";

        IntentFilter intentFilter1=new IntentFilter(action);
        intentFilter1.setPriority(10);
        registerReceiver(receiver1,intentFilter1);

        IntentFilter intentFilter2=new IntentFilter(action);
        intentFilter2.setPriority(500);
        registerReceiver(receiver2,intentFilter2);

        Button button= findViewById(R.id.third_button);
        button.setOnClickListener(view -> {
            Intent intent=new Intent();
            intent.setAction("hcy");
            sendOrderedBroadcast(intent,null);
        });


    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(receiver1);
        unregisterReceiver(receiver2);
    }
}