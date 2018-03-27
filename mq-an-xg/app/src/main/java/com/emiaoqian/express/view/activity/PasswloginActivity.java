package com.emiaoqian.express.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emiaoqian.express.R;
import com.emiaoqian.express.utils.LogUtil;
import com.emiaoqian.express.utils.ToastUtil;
import com.emiaoqian.express.utils.sharepreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiong on 2017/8/15.
 */

public class PasswloginActivity extends AppCompatActivity {

    @BindView(R.id.tb)
    Toolbar tb;
    //    @BindView(R.id.login)
//    TextView login;
    @BindView(R.id.signload)
    TextView signload;
    @BindView(R.id.forgetpassword)
    TextView forgetpassword;
    @BindView(R.id.pass_login)
    TextView passLogin;
    @BindView(R.id.phonenum)
    EditText phonenum;
    @BindView(R.id.signnum)
    EditText signnum;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_password_view);
        ButterKnife.bind(this);
        passLogin.setEnabled(false);
        inintToolbar();
        sharepreferenceUtils.saveBooleandata(PasswloginActivity.this, "isfrist", false);

        phonenum.addTextChangedListener(new TextWatcher() {
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
                editStart = phonenum.getSelectionStart();
                editEnd = phonenum.getSelectionEnd();
                if (temp.length()>11){
                    //把下面这句话删了会造成页面卡死
                    s.delete(editStart - 1, editEnd);
                    int tempSelection = editStart;
                    phonenum.setText(s);
                    //这个是为了让焦点保持在末尾，就像是保持一种视觉效果，即使最后面不能再输入了
                    phonenum.setSelection(tempSelection);
//                    phonenum.setEnabled(false);
//                    passLogin.setBackgroundResource(R.drawable.select_2loadbutton);
                }else if (temp.length()==11){
                    passLogin.setEnabled(true);
                    passLogin.setBackgroundResource(R.drawable.select_loadbutton);

                }else {
                    passLogin.setEnabled(false);
                    passLogin.setBackgroundResource(R.drawable.select_2loadbutton);
                }

            }
        });



        //验证身份
        passLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = phonenum.getText().toString().trim();
                String password = signnum.getText().toString().trim();

                LogUtil.e("--手机号-"+phone+"--密码--"+password);
                if (TextUtils.isEmpty(phone)){
                    ToastUtil.showToast("手机号为空");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    ToastUtil.showToast("密码为空");
                    return;
                }

                LogUtil.e("--if之后还会执行么");

                Intent intent = new Intent(PasswloginActivity.this, CheckidentityActivity.class);
                startActivity(intent);
                sharepreferenceUtils.saveBooleandata(PasswloginActivity.this, "islogin", false);
                finish();
            }
        });

        //切换登录方式
        signload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PasswloginActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PasswloginActivity.this, ForgetpasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void inintToolbar() {
        //这个是登录的
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
}
