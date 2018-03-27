package com.emiaoqian.express.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.emiaoqian.express.R;
import com.emiaoqian.express.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiong on 2017/8/22.
 */

public class ForgetpasswordActivity extends AppCompatActivity {


    @BindView(R.id.forgetpasswordphonenum)
    TextView forgetpasswordphonenum;
    @BindView(R.id.phonenum)
    EditText phonenum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword);
        ButterKnife.bind(this);
        //下面是监听输入，控制输入了几位数的以便控制button的颜色
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
                }else if (temp.length()==11){
                    forgetpasswordphonenum.setEnabled(true);
                    forgetpasswordphonenum.setBackgroundResource(R.drawable.select_loadbutton);

                }else {
                    forgetpasswordphonenum.setEnabled(false);
                    forgetpasswordphonenum.setBackgroundResource(R.drawable.select_2loadbutton);
                }

            }
        });

        forgetpasswordphonenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phonenum.getText().toString().trim();
                String num="[1][234567890]\\d{9}";
                if (TextUtils.isEmpty(phone)){
                    ToastUtil.showToast("输入手机号为空");
                    return;
                }else if (!phone.matches(num)){
                    ToastUtil.showToast("输入手机号不正确");
                    return;

                }


                Intent intent = new Intent(ForgetpasswordActivity.this, WritesignwordActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
