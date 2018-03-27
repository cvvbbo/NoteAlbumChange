package com.emiaoqian.express.net.bean;

/**
 * Created by xiong on 2017/8/30.
 */

public class Identitybean {


    /**
     * code : 100000
     * data : {"depInfo":"常德市公安局武陵分局","idNo":"342201198801233837","idcard_back":"uploads/offline_idcard_auth/20170830/01bf75c8aeacdea0653c15690339735c.jpg","idcard_front":"uploads/offline_idcard_auth/20170830/a56b80fb73ff7e5cd4ebd4bce8176897.jpg","name":"李南南"}
     * msg : 识别成功
     */

    private String code;
    private DataBean data;
    private String msg;

    private int errorCode;

    public int getErrorCode(){
        return errorCode;
    }




    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * depInfo : 常德市公安局武陵分局
         * idNo : 342201198801233837
         * idcard_back : uploads/offline_idcard_auth/20170830/01bf75c8aeacdea0653c15690339735c.jpg
         * idcard_front : uploads/offline_idcard_auth/20170830/a56b80fb73ff7e5cd4ebd4bce8176897.jpg
         * name : 李南南
         */

        private String depInfo;
        private String idNo;
        private String idcard_back;
        private String idcard_front;
        private String name;

        public String getDepInfo() {
            return depInfo;
        }

        public void setDepInfo(String depInfo) {
            this.depInfo = depInfo;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getIdcard_back() {
            return idcard_back;
        }

        public void setIdcard_back(String idcard_back) {
            this.idcard_back = idcard_back;
        }

        public String getIdcard_front() {
            return idcard_front;
        }

        public void setIdcard_front(String idcard_front) {
            this.idcard_front = idcard_front;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
