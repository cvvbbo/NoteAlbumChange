package com.emiaoqian.express.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.emiaoqian.express.Application.MyApplication;
import com.emiaoqian.express.net.httphelper;
import com.emiaoqian.express.utils.Constant;
import com.emiaoqian.express.utils.LogUtil;
import com.emiaoqian.express.utils.Test3Builder;

/**
 * Created by xiong on 2017/8/17.
 */

public class Homefragment extends BaseFragment {



    @Override
    public View getsuccess() {
        TextView textView=new TextView(MyApplication.mcontext);
        textView.setTextColor(Color.GRAY);
        textView.setTextSize(15);
        textView.setText("我叫主界面");
        return textView;
    }

    @Override
    public void loadData() {

        Test3Builder t3=new Test3Builder();
        String mylord = t3.Mylord(Constant.BAES_UNSIGN);

        httphelper.create().dopost(Constant.BASE_URL, mylord, new httphelper.httpcallback() {
            @Override
            public void success(String s) {
                LogUtil.e(s);
                differentstate.showsuccessView();


            }

            @Override
            public void fail(Exception e) {
                //都忘了写showerror，怎么会有显示呢。。。
                differentstate.showerrorView();
                LogUtil.e(e.toString());

            }
        });

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }


}
