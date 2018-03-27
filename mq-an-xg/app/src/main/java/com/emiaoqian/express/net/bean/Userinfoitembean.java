package com.emiaoqian.express.net.bean;

import java.util.List;

/**
 * Created by xiong on 2017/8/30.
 */

public class Userinfoitembean {


    /**
     * code : 100000
     * msg : success
     * data : [{"category":2,"name":"实名认证","status":1,"api_img":{"name":"实名认证.png","url":"1c44fa553719dd540514527417a313a9.png","local_url":"http://192.168.3.47/uploads/20170829/1c44fa553719dd540514527417a313a9.png"}},{"category":2,"name":"工作汇总","status":1,"api_img":{"name":"工作汇总.png","url":"b311eaaea3d42dec600ecd167aed7e84.png","local_url":"http://192.168.3.47/uploads/20170828/b311eaaea3d42dec600ecd167aed7e84.png"}},{"category":2,"name":"邀请好友","status":1,"api_img":{"name":"邀请好友.png","url":"fe97fd8f2a60eccde2b2cbc7ebcb23a7.png","local_url":"http://192.168.3.47/uploads/20170828/fe97fd8f2a60eccde2b2cbc7ebcb23a7.png"}},{"category":2,"name":"帮助与反馈","status":1,"api_img":{"name":"帮助与反馈.png","url":"551b0185c5b3752a77740a4b855fe425.png","local_url":"http://192.168.3.47/uploads/20170828/551b0185c5b3752a77740a4b855fe425.png"}},{"category":2,"name":"联系客服","status":1,"api_img":{"name":"联系客服.png","url":"c99a5f68b7dc86b5ead532d166bc6272.png","local_url":"http://192.168.3.47/uploads/20170828/c99a5f68b7dc86b5ead532d166bc6272.png"}},{"category":2,"name":"设置","status":1,"api_img":{"name":"设置.png","url":"0665c4f506288a18b3669bd2b626ca95.png","local_url":"http://192.168.3.47/uploads/20170828/0665c4f506288a18b3669bd2b626ca95.png"}}]
     */

    private int code;
    private String msg;
    //
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * category : 2
         * name : 实名认证
         * status : 1
         * api_img : {"name":"实名认证.png","url":"1c44fa553719dd540514527417a313a9.png","local_url":"http://192.168.3.47/uploads/20170829/1c44fa553719dd540514527417a313a9.png"}
         */

        private int category;
        private String title_name;
        private int status;
        private ApiImgBean api_img;

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getName() {
            return title_name;
        }

        public void setName(String name) {
            this.title_name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public ApiImgBean getApi_img() {
            return api_img;
        }

        public void setApi_img(ApiImgBean api_img) {
            this.api_img = api_img;
        }

        public static class ApiImgBean {
            /**
             * name : 实名认证.png
             * url : 1c44fa553719dd540514527417a313a9.png
             * local_url : http://192.168.3.47/uploads/20170829/1c44fa553719dd540514527417a313a9.png
             */


            //有用
            private String name;
            private String url;
            //有用
            private String local_url;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getLocal_url() {
                return local_url;
            }

            public void setLocal_url(String local_url) {
                this.local_url = local_url;
            }
        }
    }
}
