package com.emiaoqian.express.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.emiaoqian.express.Application.MyApplication;
import com.emiaoqian.express.R;

/**
 * Created by xiong on 2017/9/4.
 */

public class Userinforadapter extends BaseAdapter {

    String[] lefttitle={"姓名","手机号","小哥编号","小哥等级","口碑"};
    String[] righttitle={"姓名","手机号","小哥编号","小哥等级","口碑"};

    @Override
    public int getCount() {
        return lefttitle.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Myholder myholder;
        if (convertView==null){
            myholder=new Myholder();
            convertView=View.inflate(MyApplication.mcontext, R.layout.userinfor_list,null);
            myholder.leftname= (TextView) convertView.findViewById(R.id.left_name);
            myholder.rightname= (TextView) convertView.findViewById(R.id.right_name);
            convertView.setTag(myholder);
        }else {
            myholder= (Myholder) convertView.getTag();
        }
        myholder.leftname.setText(lefttitle[position]);
        myholder.rightname.setText(righttitle[position]);

        return convertView;
    }

    class  Myholder{
        TextView leftname;
        TextView rightname;
    }
}
