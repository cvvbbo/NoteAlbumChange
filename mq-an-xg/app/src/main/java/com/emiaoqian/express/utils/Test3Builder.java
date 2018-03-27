package com.emiaoqian.express.utils;

import java.util.HashMap;

/**
 * Created by xiong on 2017/8/21.
 */

public class Test3Builder {

    public static String Mylord(String unsign){



        //其实不叫封装的封装。。
        Test3 t3=new Test3();
        HashMap<String, String> tomap = t3.tomap(unsign);
        String sortmap = t3.sortmap(tomap);
        String encode = Test3.encode(sortmap);
        String token = t3.addString(encode, "MIAOQIAN_API_TOKEN");
        String upwrite = t3.upwrite(token);
        String encode1 = Test3.encode(upwrite);
        String upwrite1 = t3.upwrite(encode1);

        String s=(unsign.substring(0,unsign.length()-1));

        String newsign=s+",\"sign\":\""+upwrite1+"\"}";

        return newsign;

    }

    public static String Yougrace(String unsign){
        Test3 t3=new Test3();
        HashMap<String, String> tomap = t3.tomap(unsign);
        String sortmap = t3.sortmap(tomap);
        String encode = Test3.encode(sortmap);
        String token = t3.addString(encode, "MIAOQIAN_API_TOKEN");
        String upwrite = t3.upwrite(token);
        String encode1 = Test3.encode(upwrite);
        String upwrite1 = t3.upwrite(encode1);
        return  upwrite1;
    }


}
