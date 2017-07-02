package com.example.server.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Author       xinliu
 * Date         6/29/17
 * Time         7:18 PM
 */
public class SignUpResult {
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;


    public static final int MSG_SIGN_UP_OK = 0;
    public static final int MSG_ALREADY_EXIST = 1;
    @Expose
    @SerializedName("status_code")
    private int statusCode;
    @Expose
    @SerializedName("msg_code")
    private int msgCode;


    public SignUpResult() {
    }

    public SignUpResult(int statusCode, int msg) {
        this.statusCode = statusCode;
        this.msgCode = msg;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }
}
