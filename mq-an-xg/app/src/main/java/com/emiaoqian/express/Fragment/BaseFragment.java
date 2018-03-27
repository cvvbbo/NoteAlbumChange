package com.emiaoqian.express.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emiaoqian.express.utils.FragmentUtils;
import com.emiaoqian.express.utils.IOnFragmentBackPressed;
import com.emiaoqian.express.utils.LogUtil;
import com.emiaoqian.express.view.ui.Differentstateview;

/**
 * Created by xiong on 2017/8/16.
 */

public abstract class BaseFragment extends Fragment {


   public IOnFragmentBackPressed backPressed;

    public Differentstateview differentstate;
    Handler handler=new Handler();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        backPressed=(IOnFragmentBackPressed) getActivity();
        backPressed.setFragment(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("onCreated的savedInstanceState--"+savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


       LogUtil.e("onCreateView的savedInstanceState--"+savedInstanceState);

        //这个是复用，复用有坑，慎用，暂时没有理解2017.8.17
        //if (differentstate!=null) {
            differentstate = new Differentstateview(getContext());
            differentstate.getSuccessview(getsuccess());
            differentstate.showlodingView();
            //显示成功数据的界面一开始并不显示
            //differentstate.showsuccessView();
           // differentstate.getSuccessview(getsuccess());
            differentstate.setOnResloadlingListener(new Differentstateview.OnResloadling() {
                @Override
                public void load() {
                    differentstate.showlodingView();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            loadData();
//                        }
//                    },800);

                    loadData();

                }
            });
        //}

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                loadData();
//            }
//        },800);

        loadData();

        return differentstate;
    }




    public abstract  View getsuccess();

    public  abstract void loadData();

    public  abstract boolean onBackPressed();

}
