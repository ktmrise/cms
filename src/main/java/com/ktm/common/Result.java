package com.ktm.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private String message;
    private Object data;
    private int status;

    public Result(String msg, Object data) {
        this.message = msg;
        this.data = data;
    }

    public Result(String msg) {
        this.message = msg;
    }

    public static Result ok(Object data) {
        Result result = new Result();
        return result.setMessage("success").setData(data);

    }

    public static Result ok() {
        return new Result("success", null);
    }


    public static Result fail() {
        return new Result("fail", null);
    }
    public static Result fail(String message,Object data,int status) {
        return new Result(message, data, status);
    }

    public static Result ok(String message,Object data, int status) {
        return new Result(message, data, status);

    }




}
