package com.emiaoqian.express.view.activity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.emiaoqian.express.Application.MyApplication;
import com.emiaoqian.express.Fragment.BaseFragment;
import com.emiaoqian.express.Fragment.Homefragment;
import com.emiaoqian.express.Fragment.Mefragment;
import com.emiaoqian.express.Fragment.Taskfragment;
import com.emiaoqian.express.childfragment.CheckidentityFragment;
import com.emiaoqian.express.utils.FragmentUtils;
import com.emiaoqian.express.utils.IOnFragmentBackPressed;
import com.emiaoqian.express.utils.LogUtil;
import com.emiaoqian.express.R;
import com.emiaoqian.express.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.emiaoqian.express.utils.FragmentUtils.fragmentManager;

/**
 * Created by xiong on 2017/8/16.
 *
 * 内存泄漏什么的都是写完之后的善后工作，别急想着方面的事情
 *
 *
 */

public class BeginActivity extends AppCompatActivity implements View.OnClickListener,IOnFragmentBackPressed{


    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.rdbt1)
    RadioButton rdbt1;
    @BindView(R.id.rdbt2)
    RadioButton rdbt2;
    @BindView(R.id.rdbt3)
    RadioButton rdbt3;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;

    private  boolean isexit=false;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isexit=false;
        }
    };


    //之前这里有放
     ArrayList<BaseFragment> fragments=new ArrayList<>();
    private  FragmentTransaction beginTransaction;
    private FragmentUtils fragmentUtils;

    Homefragment homefragment=new Homefragment();
    Mefragment mefragment=new Mefragment();
    Taskfragment taskfragment=new Taskfragment();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin_activity);
        LogUtil.e("--保存的值是什么"+savedInstanceState);

        LogUtil.e("--myapplication中的值--"+MyApplication.baseFragments.size());


        //等项目完成之后再来研究黄油刀的销毁
       ButterKnife.bind(this);

        //initFragment();


        initToolbar();


        fragmentUtils = FragmentUtils.create(getSupportFragmentManager());


        rdbt1.setOnClickListener(this);
        rdbt2.setOnClickListener(this);
        rdbt3.setOnClickListener(this);



        //initFragment();
        //switchFragment(0);
        fragmentUtils.switchfragment(0,"home");


//        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//           @Override
//           public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//               switch (checkedId){
//                   case R.id.rdbt1:
//                       LogUtil.e("按钮1","执行了么");
//                       //switchFragment(0);
//
//                       fragmentUtils.switchfragment(0,"home");
//
//                       break;
//                   case R.id.rdbt2:
//                      // switchFragment(1);
//
//                       fragmentUtils.switchfragment(1,"task");
//
//
//                       break;
//                   case R.id.rdbt3:
//                      // switchFragment(2);
//
//                     fragmentUtils.switchfragment(2,"me");
//
//                       break;
//               }
//           }
//       });
    }



    @Override
    public void onClick(View v) {
        rdbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("--"+"被点击了么1");

                fragmentUtils.switchfragment(0,"home");
            }
        });

        rdbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("--"+"被点击了么2");
                fragmentUtils.switchfragment(1,"task");


            }
        });

        rdbt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("--"+"被点击了么3");
                fragmentUtils.switchfragment(2,"me");
            }
        });
    }




//    public  void switchFragment(int j) {
//        beginTransaction =getSupportFragmentManager().beginTransaction();
//
//        for (int i = 0; i < fragments.size(); i++) {
//            Fragment f = fragments.get(i);
//            if (i == j) {
//                if (f.isAdded()) {
//                    //已经添加过，直接展示
//                    beginTransaction.show(f);
//                } else {
//                    //并没有添加，则把fragment添加进来
//                    beginTransaction.add(R.id.framelayout, f);
//                    //beginTransaction.addToBackStack(null);
//                }
//            } else {
//                if (f.isAdded()) {
//                    //当遍历的和当前的不相等的时候，把这些全部隐藏起来。
//                    beginTransaction.hide(f);
//                    LogUtil.e("隐藏了那些"+f+" ");
//                }
//            }
//        }
//        //最后一定要记得提交
//        beginTransaction.commit();
//    }


    private void initToolbar() {
        setSupportActionBar(tb);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("秒签小哥");
    }



//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode==KeyEvent.KEYCODE_BACK){
//            exit();
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }




    private void exit() {
        if (!isexit){
            isexit=true;
            ToastUtil.showToast("再按一下退出");
            handler.sendEmptyMessageDelayed(0,2000);
        }else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);

    }


//    @Override
//    public void onBackPressed() {
//        //fragmentUtils.switchfragment(2,"me");
//       // fragmentUtils.backfragment(2,"me");
//
//        LogUtil.e("---哈哈");
//    }


//    @Override
//    public void onBackPressed() {
//        if(isexit){
//            //退出
//            super.onBackPressed();
//        }else{
//            //单击一次提示信息
//           ToastUtil.showToast("再按一次退出");
//            isexit=true;
//            new Thread(){
//                public void run() {
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                    //3秒之后，修改flag的状态
//                    isexit=false;
//                };
//            }.start();
//        }
//    }


    @Override
    public void onBackPressed() {
        int Count = FragmentUtils.fragmentManager.getBackStackEntryCount();
        if (Count!=0) {
            LogUtil.e("--有几个-"+Count);
            super.onBackPressed();
        }else {
            if(isexit){
            //退出
            super.onBackPressed();
        }else{
            //单击一次提示信息
           ToastUtil.showToast("再按一次退出");
            isexit=true;
            new Thread(){
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //3秒之后，修改flag的状态
                    isexit=false;
                };
            }.start();
        }
        }
    }

    @Override
    public void setFragment(BaseFragment baseFragment) {
        //MyApplication.baseFragments.add(new Homefragment());
        /**
         * 这里把fragment的实例传进来，但是怎么传呢？。。10.10
         */

    }


}
