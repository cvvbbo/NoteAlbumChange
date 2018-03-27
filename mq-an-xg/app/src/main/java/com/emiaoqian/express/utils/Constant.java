package com.emiaoqian.express.utils;

/**
 * Created by xiong on 2017/8/17.
 */

public interface Constant {


    //这里还放网址

    String IS_FRIST= "isfrist";
    String IS_LOGIN="islogin";

    String IS_IDENTITY= "isidentity";

    String IS_CHECKIDENTITY ="ischeckidentity";

    //测试
    String BASE_URL="http://192.168.3.112:8081/vx/document/receive";

    //测试
    String BAES_UNSIGN="{\"mobile\":\"18911315109\",\"appv\":\"v1\",\"timestamp\":\"1502980086119\"}";

    //发送短信
    String BASE_SEND_MSG="{\"mobile\":\"15222202530\",\"smscode\":\"9923\",\"appv\":\"v1\",\"timestamp\":\"1503214167365\"}";


    String ssss="http://192.168.3.1/";

    String BASE_SEND_URL=ssss+"v1/channelOffline/message";


    //验证登录信息
    String SIGN_CHECK_URL=ssss+"v1/channelOffline/check";

    //获取用户信息
    String GET_USER_INFO_URL=ssss+"v1/channelOffline/get";

    //上传照片
    String UP_IMAGE_URL=ssss+"v1/channelOffline/upload_idCard";


    //个人信息界面获取条目
    String GET_USERPAGER_PAGER=ssss+"v1/title/menu?XDEBUG_SESSION_START=12393";







}
