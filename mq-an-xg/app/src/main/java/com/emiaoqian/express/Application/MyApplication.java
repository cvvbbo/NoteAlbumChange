package com.emiaoqian.express.Application;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.emiaoqian.express.Fragment.BaseFragment;
import com.emiaoqian.express.utils.FragmentUtils;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by xiong on 2017/8/16.
 */

public class MyApplication extends Application {

    public static Context mcontext;
    public static ArrayList<BaseFragment> baseFragments;



    @Override
    public void onCreate() {
        super.onCreate();
        mcontext=this;
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        baseFragments=new ArrayList<>();
    }


}
