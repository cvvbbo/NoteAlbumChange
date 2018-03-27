package com.emiaoqian.express.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emiaoqian.express.Application.MyApplication;
import com.emiaoqian.express.R;
import com.emiaoqian.express.net.bean.Userinfoitembean;
import com.emiaoqian.express.utils.sharepreferenceUtils;


import java.util.List;

/**
 * Created by xiong on 2017/8/28.
 */

public class Melistadapter extends BaseAdapter {

    private final Activity a;
    private  List<Userinfoitembean.DataBean> data;
    private TextView check_name;

    int[] drawable={R.drawable.a1,R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};
    String[] title={"实名认证","工作总汇","邀请好友","帮助与反馈","联系客服","设置"};

//    public Melistadapter(Activity a, List<Userinfoitembean.DataBean> data) {
//        this.a=a;
//        this.data=data;
//    }

    public  Melistadapter(Activity a){
        this.a=a;

    }

    @Override
    public boolean isEnabled(int position) {

        //已实名之后第一个条目不可点击
        boolean check_first = sharepreferenceUtils.getbooleandata(MyApplication.mcontext, "check_first", true);
       // LogUtil.e(check_first+"--");
        if (position==0){
            return check_first;
        }
        return true;
    }

    @Override
    public int getCount() {
        //return data.size();
        //return 10;
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }


    //这个bean本身就写的很乱。。
//    @Override
//    public Userinfoitembean.DataBean getItem(int position) {
//        return data.get(position);
//    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder;
        //Userinfoitembean.DataBean item = getItem(position);
        if (convertView == null) {
            viewholder =new Viewholder();
            convertView = View.inflate(MyApplication.mcontext, R.layout.me_list_adapter, null);

            viewholder.iv = (ImageView) convertView.findViewById(R.id.ic_home);
            viewholder.tv= (TextView) convertView.findViewById(R.id.tv_home);
            check_name = (TextView) convertView.findViewById(R.id.check_name);

            convertView.setTag(viewholder);


        }else {
            viewholder = (Viewholder) convertView.getTag();
        }
            //假数据
            //viewholder.iv.setImageResource(R.drawable.needreplace1);

            //解析json数据，然后再获取图片
//            Glide.with(a).load(item.getApi_img().getLocal_url()).into(viewholder.iv);
//
//            viewholder.tv.setText(item.getName());
        Glide.with(a).load(drawable[position]).into(viewholder.iv);
        viewholder.tv.setText(title[position]);


        boolean check_first = sharepreferenceUtils.getbooleandata(MyApplication.mcontext, "check_first", true);


        //这里用eventbus来刷新已实名的状态，回很好用，毕竟sp要保存，还是要点时间的

        if (check_first){
            check_name.setVisibility(View.INVISIBLE);
        }else if (position==0&&check_first==false){
            check_name.setVisibility(View.VISIBLE);
            check_name.setText("已实名");
        }else if (position==5&&check_first==false){
            check_name.setVisibility(View.INVISIBLE);
        }

        //假数据
        //viewholder.tv.setText("我是条目");

        return convertView;
    }

    class  Viewholder{
        public  ImageView iv;
        public  TextView tv;
    }






}
