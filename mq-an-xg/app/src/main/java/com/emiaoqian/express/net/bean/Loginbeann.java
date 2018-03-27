package com.emiaoqian.express.net.bean;

/**
 * Created by xiong on 2017/8/23.
 */

public class Loginbeann {


    /**
     * status : 0
     * msg : 登录成功！
     */

//    {"status":1,"msg":"验证码不正确"}

    private int status;
    private String msg;

    public Loginbeann(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

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
