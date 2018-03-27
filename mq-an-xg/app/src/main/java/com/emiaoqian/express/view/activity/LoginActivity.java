package com.emiaoqian.express.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.emiaoqian.express.Application.MyApplication;
import com.emiaoqian.express.R;
import com.emiaoqian.express.net.bean.Loginbeann;
import com.emiaoqian.express.net.bean.Smsbean;
import com.emiaoqian.express.net.httphelper;
import com.emiaoqian.express.utils.Constant;
import com.emiaoqian.express.utils.GsonUtil;
import com.emiaoqian.express.utils.LogUtil;
import com.emiaoqian.express.utils.Netutils;
import com.emiaoqian.express.utils.SignUtils;
import com.emiaoqian.express.utils.Test3Builder;
import com.emiaoqian.express.utils.ToastUtil;
import com.emiaoqian.express.utils.sharepreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiong on 2017/8/15.
 */

public class LoginActivity extends AppCompatActivity {

    private static final int UN_CHECK = 100;
    private static final int CAN_CHECK = 200;
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.passwordload)
    TextView passwordload;
    @BindView(R.id.phonenum)
    EditText phonenum;
    //    @BindView(R.id.send_code)
//    Button sendCode;
    @BindView(R.id.signnum)
    EditText signnum;
    @BindView(R.id.send_code)
    TextView sendCode;
    @BindView(R.id.check_terms)
    CheckBox checkTerms;

    private int time = 60;

    private MyReceiver receiver;
    private LocalBroadcastManager manager;


    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UN_CHECK:
                    time--;
                    sendCode.setText("请稍后" + time + "s");
                    break;
                case CAN_CHECK:
                    time = 60;
                    sendCode.setBackgroundResource(R.drawable.select_2sendmsg);
                    sendCode.setText("重新获取");
                    //把按钮状态设置为按下
                    sendCode.setPressed(false);
                    //最开始这个是不可点击状态
                    sendCode.setEnabled(true);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        ButterKnife.bind(this);

        DisplayMetrics metrics = new DisplayMetrics();
        Display display = getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        //Log.d(TAG, “display is ” + metrics.widthPixels + “*” + metrics.heightPixels);
        LogUtil.e("--宽-"+metrics.widthPixels+"---高-"+metrics.heightPixels);


        inintToolbar();
        sharepreferenceUtils.saveBooleandata(LoginActivity.this, "isfrist", false);
        //获取短信验证码广播
        manager = LocalBroadcastManager.getInstance(this);
        receiver = new MyReceiver();
        IntentFilter intentfilter = new IntentFilter("com.miaoqian.getcode");
        manager.registerReceiver(receiver, intentfilter);


        //首先禁止点击发送短信和登录按钮
        sendCode.setEnabled(false);
        login.setPressed(false);
        login.setEnabled(false);
        login.setBackgroundResource(R.drawable.select_2loadbutton);



        //这个是检测是手机号的是否符合长度。
        phonenum.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int editStart;
            private int editEnd;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //这个长度的引用是最下面为了检测长度的
                temp=s;

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LogUtil.e("--最后输入的值--"+s);

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtil.e("---结束时候的edtext是多少--"+s);
                editStart = phonenum.getSelectionStart();
                editEnd = phonenum.getSelectionEnd();
                LogUtil.e("--"+editStart+"--"+editEnd);

                if (temp.length() > 11) {//限制长度
                    s.delete(editStart - 1, editEnd);
                    int tempSelection = editStart;
                    phonenum.setText(s);
                    //这个是为了让焦点保持在末尾，就像是保持一种视觉效果，即使最后面不能再输入了
                    phonenum.setSelection(tempSelection);

                }else if(temp.length()==11){
                    sendCode.setEnabled(true);
                    sendCode.setPressed(false);
                    sendCode.setBackgroundResource(R.drawable.select_2sendmsg);
                    LogUtil.e("--有值么-"+sendCode.isPressed());
                }else {
                    sendCode.setEnabled(false);
                    sendCode.setPressed(false);
                    sendCode.setBackgroundResource(R.drawable.select_sendmsg);
                }
            }
        });

        //监听验证码的输入框
        signnum.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int editStart;
            private int editEnd;


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp=s;

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtil.e("---结束时候的edtext是多少--"+s);
                editStart = signnum.getSelectionStart();
                editEnd = signnum.getSelectionEnd();
                LogUtil.e("--"+editStart+"--"+editEnd);

                if (temp.length() > 4) {//限制长度
                    s.delete(editStart - 1, editEnd);
                    int tempSelection = editStart;
                    signnum.setText(s);
                    //这个是为了让焦点保持在末尾，就像是保持一种视觉效果，即使最后面不能再输入了
                    signnum.setSelection(tempSelection);

                }else if(temp.length()==4){
                    login.setEnabled(true);
                    login.setPressed(false);
                   login.setBackgroundResource(R.drawable.select_loadbutton);
                    LogUtil.e("--有值么-"+sendCode.isPressed());

                }else {
                    login.setPressed(false);
                    login.setEnabled(false);
                    login.setBackgroundResource(R.drawable.select_2loadbutton);


                }

            }
        });


        /**
         * 验证码无论对错都能进来9.28.。。
         *
         */

        //登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Netutils.isNetworkAvalible(MyApplication.mcontext)) {
                    LogUtil.e("当前网络可用");
                    LogUtil.e("当前条款是否勾选--"+checkTerms.isChecked());
                    if (checkTerms.isChecked()!=false){
                        ToastUtil.showToast("条款还未勾选");
                    }else if (checkTerms.isChecked()==false){
                        Logininto();
                    }


                } else {
                    LogUtil.e("当前网络不可用");
                    ToastUtil.showToast("当前网络不可用");
                    Netutils.checkNetwork(LoginActivity.this);
                }


            }
        });


        //账号密码登录
        passwordload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, PasswloginActivity.class);
                startActivity(intent);
                finish();
                //登录状态的保存还是在登录以后，不管是用什么登录的
                //这里以后也是要判断界面的sp存储的
            }
        });


        //发送验证码
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getsignnum();

                if (Netutils.isNetworkAvalible(MyApplication.mcontext)) {
                    LogUtil.e("当前网络可用");
                    Getsignnum();
                } else {
                    LogUtil.e("当前网络不可用");
                    ToastUtil.showToast("当前网络不可用");
                    Netutils.checkNetwork(LoginActivity.this);
                }
            }
        });
    }


    //自动获取短信广播，获取广播发送者传递过来的数据
    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //收到广播 填写验证码
            String code = intent.getStringExtra("code");
            signnum.setText(code);

        }

    }


    private void inintToolbar() {
        //这个是登录的toolbar
        setSupportActionBar(tb);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayShowHomeEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle("秒签小哥");

        //这个通用，自定义的还是非自定义的都是通用的。
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    //验证短信验证码，用于登录
    public void Logininto() {
        String phone = phonenum.getText().toString().trim();


        String codenum = signnum.getText().toString().trim();

        if (TextUtils.isEmpty(phone)){
            ToastUtil.showToast("手机号不能为空");
            return;
        }

        if (TextUtils.isEmpty(codenum)) {
            ToastUtil.showToast("验证码不能空");

        } else {
            //String phone = phonenum.getText().toString().trim();
            LogUtil.e("能获取到手机号么" + phone);
            LogUtil.e("验证码" + codenum);
            //传json
            String s = SignUtils.getsmsSign(phone, codenum);
            //验证
            String mylord = Test3Builder.Mylord(s);
            LogUtil.e("--------------------" + mylord);

            httphelper.create().dopost(Constant.SIGN_CHECK_URL, mylord, new httphelper.httpcallback() {
                @Override
                public void success(String s) {

                    /**
                     *
                     * {"code":404,"msg":"module not exists:v1","time":1506590361}
                     * 这里有个很诡异的错误就是服务器并没有连接失败，但是却返回了这个，能成功连接，但是并不是正常成功时候返回的数据
                     *
                     *
                     */

                    LogUtil.e("无论成功失败都有值么--"+s);
                    Loginbeann loginbeann = GsonUtil.parseJsonToBean(s, Loginbeann.class);
                    //当这个s返回的不是正常成功返回的数据的时候，下面这个status获取不到值，默认就是0.
                    int status = loginbeann.getStatus();
                    String msg = loginbeann.getMsg();
                    //添加入集合储存起来。
                    if (status == 0) {
                        String phone = phonenum.getText().toString().trim();
                        sharepreferenceUtils.saveStringdata(LoginActivity.this, "getphone", phone);
                        Intent intent = new Intent(LoginActivity.this, BeginActivity.class);
                        startActivity(intent);
                        sharepreferenceUtils.saveBooleandata(LoginActivity.this, "islogin", false);
                        finish();
                        LogUtil.e("登录成功");

                    } else if (status == 1) {
                        ToastUtil.showToast(msg);
                        LogUtil.e(s);
                    }

                }

                @Override
                public void fail(Exception e) {
                    //这里以后再写一个类似于3种状态登录的
                    LogUtil.e(e + " ");
                    ToastUtil.showToast("服务器出错了,稍等~");
                    LogUtil.e("---验证码失败了"+e);

                }
            });
        }

    }

    //获取验证码
    public void Getsignnum() {

        //判断为不为空，手机号是不是正确的。。以后再考虑

        //需求是当手机号码没有填满的时候，那个发短信的按钮是按不出来的

        String phone = phonenum.getText().toString().trim();
        //正则的匹配
        String num="[1][234567890]\\d{9}";//注意正则表达式这里是

        //首先输入了11位就能正常显示，然后再正则()

        //2017.9.27



        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("号码不能为空");

        } else if (phone.matches(num)==false){
            ToastUtil.showToast("输入号码不正确");

        }else {

            String s = SignUtils.sendsmsSign(phone);

            LogUtil.e("得到的字符串" + s);
            LogUtil.e("原来的字符串" + Constant.BASE_SEND_MSG);
            LogUtil.e("");

            String mylord = Test3Builder.Mylord(s);
            LogUtil.e(mylord);

            httphelper.create().dopost(Constant.BASE_SEND_URL, mylord, new httphelper.httpcallback() {

                /**
                 *
                 * 这里还有个隐藏的bug，就是当服务端在调试的时候
                 * @param s
                 */

                @Override
                public void success(String s) {
                    Smsbean smsbean = GsonUtil.parseJsonToBean(s, Smsbean.class);
                    String msg = smsbean.getMsg();
                    int status = smsbean.getStatus();
                    if (status == 2) {
                        ToastUtil.showToast(msg + " ,每日短信次数用尽");
                    } else if (status == 0) {

                        //设置倒计时
                        sendCode.setBackgroundResource(R.drawable.select_sendmsg);
                        sendCode.setEnabled(false);
                        new Thread() {
                            @Override
                            public void run() {
                                while (time > 1) {
                                    //别设置成1000，太慢了。，要么就放在下面试试
                                    SystemClock.sleep(600);
                                    h.sendEmptyMessage(UN_CHECK);
                                }
                                h.sendEmptyMessage(CAN_CHECK);

                            }
                        }.start();
                        ToastUtil.showToast(msg);
                    }
                    LogUtil.e(s);

                }

                @Override
                public void fail(Exception e) {
                    ToastUtil.showToast("灰常抱歉！服务器出错了");
                    LogUtil.e(e + " ");
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterReceiver(receiver);
    }
}
