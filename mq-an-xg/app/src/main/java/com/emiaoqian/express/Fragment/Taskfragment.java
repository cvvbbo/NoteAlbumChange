package com.emiaoqian.express.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.emiaoqian.express.Application.MyApplication;
import com.emiaoqian.express.R;
import com.emiaoqian.express.utils.LogUtil;

/**
 * Created by xiong on 2017/8/17.
 */

public class Taskfragment extends BaseFragment {


    private Button button;

    @Override
    public View getsuccess() {
//        TextView textView=new TextView(MyApplication.mcontext);
//        textView.setTextColor(Color.GRAY);
//        textView.setTextSize(15);
//        textView.setText("任务界面");
//        return textView;

        View view = View.inflate(getActivity(), R.layout.task_view, null);
        button = (Button) view.findViewById(R.id.bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("--任务界面的按钮执行了么");
            }
        });
        return  view;


    }

    @Override
    public void loadData() {
        differentstate.showsuccessView();
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }


}
