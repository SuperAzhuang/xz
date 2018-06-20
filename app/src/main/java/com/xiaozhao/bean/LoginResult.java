package com.xiaozhao.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/20.
 */

public class LoginResult {


    /**
     * status : 1
     * msg : 登录成功
     * data : [{"token":"7Quj3YoDbyO1fxlpTe9B","nickname":"1515","avator":""}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : 7Quj3YoDbyO1fxlpTe9B
         * nickname : 1515
         * avator :
         */

        private String token;
        private String nickname;
        private String avator;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvator() {
            return avator;
        }

        public void setAvator(String avator) {
            this.avator = avator;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "token='" + token + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", avator='" + avator + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
