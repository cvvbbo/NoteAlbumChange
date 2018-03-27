package com.emiaoqian.express.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.emiaoqian.express.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckidentityActivity extends AppCompatActivity {


    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.identity)
    Button identity;

    //登录之后的第一个界面。但是如果下次的已验证就不再是这个界面了，是另一个界面了
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_identity);
        ButterKnife.bind(this);

        inintToolbar();
        identity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CheckidentityActivity.this,UppersonidentityActivity.class);
                startActivity(intent);

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
