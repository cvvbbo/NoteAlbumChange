package com.emiaoqian.express.childfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.emiaoqian.express.Fragment.BaseFragment;

/**
 * Created by xiong on 2017/8/28.
 */

public class CheckidentityFragment extends BaseFragment {

    private  boolean isexit=false;

    @Override
    public View getsuccess() {
        TextView view=new TextView(getActivity());
        view.setText("www。baidu。com");
        view.setTextSize(16);
        view.setTextColor(Color.GRAY);
        return view;
    }



    @Override
    public void loadData() {
        differentstate.showsuccessView();

    }

    @Override
    public boolean onBackPressed() {
        if (!isexit){
            isexit=true;
            return true;

        }

        return false;
    }


}
