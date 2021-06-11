package com.qtt.study.pay.entity;

import lombok.Data;

/**
 * @author Ellie
 * @date 2021-06-11 下午4:11
 */
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private String message;
    private T data;

    public Result ok(T t){
        this.setCode(1000);
        this.setMsg("成功");
        this.setMessage("成功");
        this.setData(t);
        return this;
    }

    public Result fail(String msg){
        this.setCode(1001);
        this.setMsg(msg);
        this.setMessage(msg);
        return this;
    }


    public Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.setMessage(msg);
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.setMessage(msg);
    }

}
