package com.emiaoqian.express.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.emiaoqian.express.utils.Constant;
import com.emiaoqian.express.utils.sharepreferenceUtils;
import com.emiaoqian.express.R;

/**
 * Created by xiong on 2017/8/14.
 */

public class SplashActivity extends Activity {


    //闪屏界面是检查更新用的
    //检查更新是肯定要写的，不然一个版本就是最终版本

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);




        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                boolean isfrist = sharepreferenceUtils.getbooleandata(SplashActivity.this, Constant.IS_FRIST, true);
                boolean isLogin = sharepreferenceUtils.getbooleandata(SplashActivity.this, Constant.IS_LOGIN, true);
                boolean isidentity = sharepreferenceUtils.getbooleandata(SplashActivity.this, Constant.IS_IDENTITY, true);
                boolean ischeckidentity= sharepreferenceUtils.getbooleandata(SplashActivity.this, Constant.IS_CHECKIDENTITY,true);


                if (isfrist) {
                    Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                    startActivity(intent);
                    //sharepreferenceUtils.saveBooleandata(SplashActivity.this, "isfrist", false);
                    finish();
                } else if (isLogin) {
                    Log.e("----", isLogin + "" + "验证登录");
                    Intent Loginintent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(Loginintent);
                    finish();
                } else if (isidentity) {
                    Log.e("----", isidentity + "验证身份");
                    Intent isIdentityintent = new Intent(SplashActivity.this, CheckidentityActivity.class);
                    startActivity(isIdentityintent);
                    finish();


                    //验证待审核
                }else if (ischeckidentity){
                    Intent intent = new Intent(SplashActivity.this, Loadcheckidentity.class);
                    Log.e("SplashActivity", "进入主界面");
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashActivity.this, BeginActivity.class);
                    Log.e("SplashActivity", "进入主界面");
                    startActivity(intent);
                    finish();
                }

            }

            },2000);

    }


}
