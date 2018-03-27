package com.emiaoqian.express.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;

import com.emiaoqian.express.R;
import com.emiaoqian.express.utils.LogUtil;
import com.emiaoqian.express.view.ui.IdentifyingCodeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiong on 2017/8/22.
 */

public class WritesignwordActivity extends AppCompatActivity {


    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.sign_code)
    IdentifyingCodeView signCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_sign);
        ButterKnife.bind(this);
        signCode.setInputCompleteListener(new IdentifyingCodeView.InputCompleteListener() {

            //输入监听，
            @Override
            public void inputComplete() {

                //除此之外还要判断验证码是否正确，等其他问题
                if (signCode.getTextcurrentLenght()==4){
                    Intent intent=new Intent(WritesignwordActivity.this,BeginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void deleteContent() {

            }
        });


    }
}
