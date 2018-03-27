package com.emiaoqian.express.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//短信的内容通过意图进行传递  通过Bundle传递 获取的key就是pdus
		//Bundle b = new Bundle();
		//拿到的内容是一个byte的二维数组  如果是超长短信 每一条短信对应一个byte数组
		Object[] obj = (Object[]) intent.getExtras().get("pdus");

		for(Object obj1:obj){
			//createFromPdu 从byte数组创建一条短信
			SmsMessage message = SmsMessage.createFromPdu((byte[])obj1);
			//短信内容
			String content = message.getMessageBody();

			//发送短信的号码
			String address = message.getOriginatingAddress();


			Pattern pattern=Pattern.compile("(\\d{4})");
			Matcher matcher =pattern.matcher(content);

			if(matcher.find()){
				String group = matcher.group(0);
				Log.e("---",group);
				System.out.println("收到来自"+address+"的短信内容是:"+content);
				//在模拟器上是收不到的20位数字的短信的，但是在真机上就行！
				System.out.println("收到来自"+address+"的短信内容是:"+content+"---"+group);
				//判断address 是不是来自我的短信外呼中心 如果是 说明是我的注册的验证码
				if("10655025235849970165".equals(address)||"10655025235629970165".equals(address)||"10655025235859970165".equals(address)){
					LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
					Intent intent2 = new Intent("com.miaoqian.getcode");
					intent2.putExtra("code", group);
					manager.sendBroadcast(intent2);
				}
			}



		}
	}

}
