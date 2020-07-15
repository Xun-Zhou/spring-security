package com.example.demo.moudle;

import java.io.Serializable;

/**
 * 返回
 */
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String CODE_SUCCESS = "0";

    public static final String MSG_SUCCESS = "SUCCESS";

    private String code = "0";

    private String description = "SUCCESS";

    private T data;

    public Response() {
    }

    public Response(T data) {
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        return result(data, CODE_SUCCESS, MSG_SUCCESS);
    }

    public static Response success() {
        return success((Object) null);
    }

    public static Response error() {
        return error("-1", "ERROR");
    }

    public static Response error(String code, String extMessage) {
        Response response = new Response((Object) null);
        response.code = code;
        response.description = extMessage;
        return response;
    }

    public static <T> Response<T> result(T data, String code, String msg) {
        Response<T> response = new Response();
        response.setCode(code);
        response.setData(data);
        response.setDescription(msg);
        return response;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
