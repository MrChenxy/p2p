package com.bjpowernode.p2p.vo;

import java.io.Serializable;

/**
 * @author Mr.chenxy
 * @date 2020/12/21
 */
public class ReturnObject implements Serializable {

    private Integer code;
    private String message;
    private Object retData;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRetData() {
        return retData;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }
}
