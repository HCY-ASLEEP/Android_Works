package com.example.my_lesson_three;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootCompleteReceiver extends BroadcastReceiver {
    //这个开机启动的广播有点特殊，这个广播可以通过静态的方式来注册
    //因为开机启动这个广播其实是在应用还没有启动的时候来工作的
    //所以它也没有出现在任何一个活动的代码里面
    //所以它只能通过静态来注册
    //静态注册是在xml里面就已经注册好的了,在活动中只需要sendBroadcast就可以发送广播了
    //动态注册是指在活动的onCreate函数也就是活动启动阶段才动态注册过滤器intentFilter
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Boot complete", Toast.LENGTH_SHORT).show();
    }
}
