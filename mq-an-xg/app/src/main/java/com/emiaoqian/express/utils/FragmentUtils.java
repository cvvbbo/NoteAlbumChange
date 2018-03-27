package com.emiaoqian.express.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.emiaoqian.express.Application.MyApplication;
import com.emiaoqian.express.Fragment.BaseFragment;
import com.emiaoqian.express.Fragment.Homefragment;
import com.emiaoqian.express.Fragment.Mefragment;
import com.emiaoqian.express.Fragment.Taskfragment;
import com.emiaoqian.express.R;
import com.emiaoqian.express.childfragment.CheckidentityFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xiong on 2017/9/17.
 */

public class FragmentUtils {

    public static FragmentManager  fragmentManager;


    public  FragmentTransaction beginTransaction;

    public ArrayList<BaseFragment> fragments;

    private  static FragmentUtils fu=new FragmentUtils();



    public static FragmentUtils create(FragmentManager f){
        fragmentManager=f;
        return  fu;
    }

    private   FragmentUtils(){

        initFragment();

    }


    public  void switchfragment(int j,String tag){

        beginTransaction = fragmentManager.beginTransaction();

        for (int i = 0; i < fragments.size(); i++) {
            Fragment f =fragments.get(i);
            if (i == j) {
                if (f.isAdded()) {
                    //已经添加过，直接展示
                    beginTransaction.show(f);
                } else {
                    //并没有添加，则把fragment添加进来
                    beginTransaction.add(R.id.framelayout, f,f.getClass().getSimpleName());
                    if (tag!="home"&&tag!="me"&&tag!="task"){
                        beginTransaction.addToBackStack(tag);
                    }

                   // beginTransaction.addToBackStack(tag);


                }
            } else {
                if (f.isAdded()) {
                    //当遍历的和当前的不相等的时候，把这些全部隐藏起来。
                    beginTransaction.hide(f);
                    LogUtil.e("隐藏了那些"+f+" ");
                }
            }

        }
        //最后一定要记得提交
        beginTransaction.commit();
    }


        //这个是想要全局动态添加Fragment，但是目前是失败了。。2017.9.25
//    public  void switchfragment(int j,String tag){
//
//        beginTransaction = fragmentManager.beginTransaction();
//       // LogUtil.e("--在工具类里面myapplication中的值--"+IOnFragmentBackPressed.f.size());
//
//
//        for (int i = 0; i < MyApplication.baseFragments.size(); i++) {
//            LogUtil.e("--"+i+"的值是多少");
//            Fragment f =MyApplication.baseFragments.get(i);
//            if (i == j) {
//                if (f.isAdded()) {
//                    //已经添加过，直接展示
//                    beginTransaction.show(f);
//                } else {
//                    //并没有添加，则把fragment添加进来
//                    beginTransaction.add(R.id.framelayout, f,f.getClass().getSimpleName());
////                    if (tag!="home"&&tag!="me"&&tag!="task"){
////                        beginTransaction.addToBackStack(tag);
////                    }
//                    beginTransaction.addToBackStack(tag);
//
//
//                }
//            } else {
//                if (f.isAdded()) {
//                    //当遍历的和当前的不相等的时候，把这些全部隐藏起来。
//                    beginTransaction.hide(f);
//                    LogUtil.e("隐藏了那些"+f+" ");
//                }
//            }
//
//        }
//        //最后一定要记得提交
//        beginTransaction.commit();
//    }





    public  void initFragment(){
        LogUtil.e("BeginActivity","执行了么");
        fragments=new ArrayList<>();
        fragments.add(new Homefragment());
        fragments.add(new Taskfragment());
        fragments.add(new Mefragment());
        fragments.add(new CheckidentityFragment());
    }






     //下面这个也是个思路，以后再试吧2017.10.9
//    public void backfragment(int i,String tag){
//        Fragment fragment = fragmentManager.findFragmentByTag(new CheckidentityFragment().getClass().getSimpleName());
//        LogUtil.e(fragment.isAdded()+"--判断是否添加");
//        if (fragment.isAdded()){
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        }
//
//        LogUtil.e("--查找tag的值--"+fragment);
//        switchfragment(i,tag);
//    }










}
