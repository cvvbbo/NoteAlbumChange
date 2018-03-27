package com.emiaoqian.express.net.bean;

/**
 * Created by xiong on 2017/8/22.
 */

public class Smsbean {


    /**
     * status : 0
     * msg : 短信发送成功
     */
/*
    {
        "status": 2,
            "msg": "短信发送失败"         (这个是短信发送次数用尽了的提示)
    }*/

    /***
     * 如果是手机号码格式的错误，则是这样的返回提示
     * {"code":400,"msg":{"mobile":"mobile规则错误"},"error_code":10000,"time":1503404677}
     *
     */

    /**
     * {"status":0,"msg":"短信发送成功"} 这个是成功返回短信的json串，
     *
     */

    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
