package com.emiaoqian.express.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.emiaoqian.express.utils.LogUtil;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by xiong on 2017/9/4.
 */

public class Mypushreceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
       // Log.d(TAG, "onReceive - " + intent.getAction());

        LogUtil.e("onReceive - " + intent.getAction());

        String type = bundle.getString(JPushInterface.EXTRA_EXTRA);
        LogUtil.e("添加的额外字段是-"+type);

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            //Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            LogUtil.e("[MyReceiver] 接收Registration Id : " + regId);
        }

    }
}
