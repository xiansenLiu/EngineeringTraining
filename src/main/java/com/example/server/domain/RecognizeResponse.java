package com.example.server.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.List;

/**
 * Author       xinliu
 * Date         6/28/17
 * Time         2:39 PM
 */
public class RecognizeResponse {
    public static final String STATE_SUCCESS = "ok";
    public static final String STATE_ERROR = "error";


    public static final int CODE_NOT_SIGN_IN = 195;
    public static final int CODE_INTERNAL_ERROR = 196;
    public static final int CODE_INVALID_IMAGE = 197;
    public static final int CODE_CONNOT_RECOGNIZE = 198;
    public static final int CODE_RECOGNIZE_FAILED = 199;
    public static final int CODE_SUCCESS = 200;

    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("code")
    private int code;
    @Expose
    @SerializedName("result")
    private List<String> result;

    public RecognizeResponse() {
    }

    public RecognizeResponse(String state, int code, List<String> result) {
        this.code = code;
        this.status = state;
        this.result = result;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

}
