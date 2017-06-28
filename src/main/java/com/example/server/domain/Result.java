package com.example.server.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Author       xinliu
 * Date         6/28/17
 * Time         2:39 PM
 */

public class Result {
    @Expose
    @SerializedName("state")
    private String state;
    @Expose
    @SerializedName("msg")
    private String msg;
    @Expose
    @SerializedName("result")
    private String result;
    @SerializedName("md5")
    private String md5;

    public Result() {
    }

    public Result(String state, String msg, String result, String md5) {
        this.state = state;
        this.msg = msg;
        this.result = result;
        this.md5 = md5;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
