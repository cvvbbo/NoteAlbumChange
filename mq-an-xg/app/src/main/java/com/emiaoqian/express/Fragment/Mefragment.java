package com.emiaoqian.express.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.emiaoqian.express.Application.MyApplication;
import com.emiaoqian.express.adapter.Melistadapter;
import com.emiaoqian.express.childfragment.CheckidentityFragment;
import com.emiaoqian.express.net.bean.Userinfoitembean;
import com.emiaoqian.express.net.httphelper;
import com.emiaoqian.express.utils.Constant;
import com.emiaoqian.express.utils.FragmentUtils;
import com.emiaoqian.express.utils.GsonUtil;
import com.emiaoqian.express.utils.LogUtil;
import com.emiaoqian.express.utils.SignUtils;
import com.emiaoqian.express.utils.Test3Builder;
import com.emiaoqian.express.utils.ToastUtil;
import com.emiaoqian.express.utils.sharepreferenceUtils;
import com.emiaoqian.express.view.activity.UppersonidentityActivity;
import com.emiaoqian.express.R;
import com.emiaoqian.express.view.activity.Userinfor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xiong on 2017/8/17.
 */

public class Mefragment extends BaseFragment {


    @BindView(R.id.rltext1)
    RelativeLayout rltext1;
    private ListView listView;
    private View view;
    private RelativeLayout relativeLayout;
    private List<Userinfoitembean.DataBean> data1 = new ArrayList<>();
    private Melistadapter adapter;
    private TextView tv;
    private FrameLayout frameLayout;
    private RelativeLayout parentframe;


    @Override
    public View getsuccess() {
        view = View.inflate(getActivity(), R.layout.me_fragment, null);
        listView = (ListView) view.findViewById(R.id.me_list);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.rltext1);
        frameLayout = (FrameLayout) view.findViewById(R.id.childframe);
        parentframe = (RelativeLayout) view.findViewById(R.id.parentframe);
        //显示名字的
        tv = (TextView) view.findViewById(R.id.useinfor);

        parentframe.setVisibility(View.VISIBLE);
        frameLayout.setVisibility(View.GONE);
        //FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        LogUtil.e("执行了么--fragment的显示");


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyApplication.mcontext, Userinfor.class);
                startActivity(intent);

            }
        });

        //第一次进来的时候显示的文字
        boolean check_first = sharepreferenceUtils.getbooleandata(MyApplication.mcontext, "check_first", true);
        if (check_first){
            LogUtil.e(check_first+"第一次进来");

            tv.setText("未实名");
            //这里不能改状态，不然有人可能是只是看看
        }else  if (!check_first){
            LogUtil.e(check_first+"不是第一次进来");
            //实名认证之后的名字
            String user_name = sharepreferenceUtils.getStringdata(MyApplication.mcontext, "user_name", "");
            tv.setText(user_name);

        }
        String user_name = sharepreferenceUtils.getStringdata(MyApplication.mcontext, "user_name", "");
        LogUtil.e(user_name+" ");

        LogUtil.e("有值么" + data1);

        //adapter = new Melistadapter(getActivity(), data1);
        adapter=new Melistadapter(getActivity());
        listView.setAdapter(adapter);
        return view;

    }

    @Override
    public void loadData() {
        differentstate.showsuccessView();

        //这个号码也是方法sp存储里面的，因为获取个人中心界面，需要上传的数据还有自己的手机号
//        String getphone = sharepreferenceUtils.getStringdata(MyApplication.mcontext, "getphone", "");
//        LogUtil.e(getphone);
//        //拼接字符串
//        String getuserinfo = SignUtils.Getuserinfo(getphone);
//        //加密
//        String mylord = Test3Builder.Mylord(getuserinfo);
//        httphelper.create().dopost(Constant.GET_USER_INFO_URL, mylord, new httphelper.httpcallback() {
//            @Override
//            public void success(String s) {
//                //differentstate.showsuccessView();
//                LogUtil.e("第一个接口"+s);
//
//            }
//
//            @Override
//            public void fail(Exception e) {
//                //differentstate.showerrorView();
//
//            }
//        });

        //这个是缓存网络数据的sp
//        String me_page = sharepreferenceUtils.getStringdata(getActivity(), "me_page", "");
//        if (!TextUtils.isEmpty(me_page)) {
//            Parse(me_page);
//            LogUtil.e("通过缓存进来");
//            //zhbj这里故意不写判断是为了源源不断的有值，因为if执行之后，后面没有判断，还是会执行。。
//        } else {
//            getDate();
//            LogUtil.e("第一次进来");
//        }


    }

    @Override
    public boolean onBackPressed() {
        return false;
    }


//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        //    super.onSaveInstanceState(outState);
//    }



    private void getDate() {
        //第二个接口
        String getuseriteminfo = SignUtils.Getuseriteminfo();
        String mylord1 = Test3Builder.Mylord(getuseriteminfo);
        httphelper.create().dopost(Constant.GET_USERPAGER_PAGER, mylord1, new httphelper.httpcallback() {
            @Override
            public void success(String s) {
                //GsonUtil.parseJsonToList(s,new TypeToken<List<Userinfoitembean>>(){}.getType());
                // differentstate.showsuccessView();
                LogUtil.e("第二个接口"+s);

                //缓存json
                sharepreferenceUtils.saveStringdata(getActivity(), "me_page", s);
                Parse(s);
            }

            @Override
            public void fail(Exception e) {
            }
        });
    }

    private void Parse(String s) {
        Userinfoitembean userinfoitembean = GsonUtil.parseJsonToBean(s, Userinfoitembean.class);
        List<Userinfoitembean.DataBean> itemdata = userinfoitembean.getData();

        //先创建一个集合，然后把解析的集合数据放在自己创建的集合里面
        data1.addAll(itemdata);
        adapter.notifyDataSetChanged();
        differentstate.showsuccessView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                switch (position) {
                    case 0:
                        //用不用fragment以后再说吧。。
                        Intent intent = new Intent(getActivity(), UppersonidentityActivity.class);
                        //startActivity(intent);
                        startActivityForResult(intent, 1);
                        break;
                    case 1:
                        ToastUtil.showToast("我被点击了" + position);
                        break;
                    case 2:
                        ToastUtil.showToast("我被点击了" + position);
                        break;
                    case 3:
                        ToastUtil.showToast("我被点击了" + position);
                        break;
                    case 4:
                        ToastUtil.showToast("我被点击了" + position);
                        break;
                    case 5:


                        /***
                         *
                         * 贼坑。。。这里只需要添加addbacktostack即可，不用添加poptostack。。就能解决一系类发生的重叠问题
                         * 并且还能够回退。。o(╥﹏╥)o  10.9，待会研究一下addtobackstack里面的string对应的popbackstack中的参数
                         *
                         *
                         */
                        FragmentUtils fragmentUtils = FragmentUtils.create(getFragmentManager());
                        fragmentUtils.switchfragment(3,"setting");

                        //fragment和fragment的嵌套用getchildfragmentmanger
                        //如果是getchildfragmentmanger那么替换的容器是当前所在的fragment，而不是当前所在fragment的父容器
//                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//                        fragmentTransaction.addToBackStack(null).add(R.id.rltext1,new CheckidentityFragment()).commit();
//                         me.setVisibility(View.INVISIBLE);



                        /***
                         * 这个方法也失败了，第一级fragment里面添加fragment，使用setvisibility。布局也进行了相应的改变
                         * 但是getsupportfragmentmanger和getchildfragmentmanger貌似有冲突，给getchildfragmentmanger添加了
                         * 回退栈之后并不会回退。。10.9
                         *
                         */
//                        parentframe.setVisibility(View.INVISIBLE);
//                        frameLayout.setVisibility(View.VISIBLE);
//
//                        //这个是在子类的fragment中添加的fragment信息
//                        FragmentTransaction childTransaction = getChildFragmentManager().beginTransaction();
//
//                        Bundle bundle=new Bundle();
//                        bundle.putBoolean("mefragment",true);
//                        CheckidentityFragment checkidentityFragment=new CheckidentityFragment();
//                        checkidentityFragment.setArguments(bundle);
//
//                        childTransaction.add(R.id.childframe,checkidentityFragment).addToBackStack(null).commit();





                        ToastUtil.showToast("我被点击了" + position);
                        break;
                }


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //这个是防止什么都没填直接返回来的情况，这时候data会报错，然后崩溃，这里的思路就是告诉，怎么更具崩溃来解决错误。。
        //不一定去网上找，自己更具错误也是能推出来的
        if (data==null){
            return;
        }
        if (requestCode == 1) {
            String user_name = data.getStringExtra("user_name");
            LogUtil.e("返回的界面" + user_name);
            //String user_name1 = sharepreferenceUtils.getStringdata(MyApplication.mcontext, "user_name", "");
          tv.setText(user_name);

            //这里返回的已实名并不能实时刷新。。。要退出了一次应用之后才能刷新
            String me_page = sharepreferenceUtils.getStringdata(getActivity(), "me_page", "");
            data1.clear();
            Parse(me_page);
            adapter.notifyDataSetChanged();
        }

    }








}
