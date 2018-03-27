package com.emiaoqian.express.view.ui;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.emiaoqian.express.Application.MyApplication;
import com.emiaoqian.express.R;

/**
 * Created by xiong on 2017/8/16.
 */

public class Differentstateview  extends FrameLayout {

    private View errorview;
    private View loadlingview;
    private View successview;

    public Differentstateview(@NonNull Context context) {
        this(context,null);
    }

    public Differentstateview(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public Differentstateview(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initview();
    }


    public void initview(){
        loadlingview = LayoutInflater.from(MyApplication.mcontext).inflate(R.layout.page_loading, null);
        addView(loadlingview);
        errorview = LayoutInflater.from(MyApplication.mcontext).inflate(R.layout.page_error, null);
        Button btn_reload = (Button) errorview.findViewById(R.id.btn_reload);

        //就是这样写的，固定的套路
        btn_reload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    listener.load();
                }
            }
        });
        addView(errorview);

        //这里自己写的时候少了个hidde全部界面的方法
        //hideAll();

    }

    public  void  getSuccessview(View success){
        successview = success;
        if (successview!=null){
            successview.setVisibility(INVISIBLE);
            addView(successview);
        }else {
            throw new IllegalArgumentException("出错啦~~");
        }
    }

    public void hideAll(){
        errorview.setVisibility(INVISIBLE);
        loadlingview.setVisibility(INVISIBLE);
        if (successview!=null){
            successview.setVisibility(INVISIBLE);
        }
    }


    //这里成功失败的界面是给子fragment实现了网络连接，在网络连接里面像接口那样显示成功的view，显示失败的view。
    public  void  showerrorView(){
        hideAll();
        errorview.setVisibility(VISIBLE);
    }


    //basefragment只是负责显示加载的。但是显示加载成功界面也是现在fragment里面先加载着，然后在
    public  void  showlodingView(){
        hideAll();
        loadlingview.setVisibility(VISIBLE);
    }

    public  void  showsuccessView(){
        hideAll();
        successview.setVisibility(VISIBLE);
    }


    /***
     *
     * 这个实际上是先实现点击按钮的实现，然后再实现
     *
     *
     */

    private  OnResloadling  listener;
    public  void  setOnResloadlingListener(OnResloadling l){
        listener=l;
    }


    public interface  OnResloadling{
        void load();
    }
}

