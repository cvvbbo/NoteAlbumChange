package com.emiaoqian.express.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.emiaoqian.express.utils.sharepreferenceUtils;
import com.emiaoqian.express.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiong on 2017/8/16.
 */

public class Loadcheckidentity extends AppCompatActivity {

    @BindView(R.id.bt_load_check)
    Button btLoadCheck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_check_identity);
        ButterKnife.bind(this);

        btLoadCheck.setOnClickListener(new View.OnClickListener() {
            int i=0;
            @Override
            public void onClick(View v) {
                //内存泄漏问题。。
                if (i==0) {
                    Toast.makeText(Loadcheckidentity.this, "模拟数据,身份审核中", Toast.LENGTH_SHORT).show();
                    i++;
                }else if (i==1){
                    Toast.makeText(Loadcheckidentity.this, "模拟数据，恭喜。身份通过", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Loadcheckidentity.this,BeginActivity.class);
                    startActivity(intent);
                    sharepreferenceUtils.saveBooleandata(Loadcheckidentity.this, "ischeckidentity",false);
                }
            }
        });

    }
}
