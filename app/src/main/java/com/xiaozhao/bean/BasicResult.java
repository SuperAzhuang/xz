package com.xiaozhao.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/20.
 */

public abstract class BasicResult implements Serializable {

    /**
     * status : 1
     * msg : 获取验证码成功
     * data : []
     */

    public int status;
    public String msg;
    public List<?> data;

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
    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BasicResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
