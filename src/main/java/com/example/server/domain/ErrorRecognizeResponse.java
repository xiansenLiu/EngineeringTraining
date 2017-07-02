package com.example.server.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Author       xinliu
 * Date         7/2/17
 * Time         11:18 AM
 */

public class ErrorRecognizeResponse {
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("code")
    private int code;
    @Expose
    @SerializedName("result")
    private String result;

    public ErrorRecognizeResponse() {
    }

    public ErrorRecognizeResponse(String status, int code, String result) {
        this.status = status;
        this.code = code;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
