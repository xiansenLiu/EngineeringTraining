package com.example.server.domain;

/**
 * Author       xinliu
 * Date         6/28/17
 * Time         2:39 PM
 */
public class LicenseResult {

    private String state;
    private String result;
    private String md5;

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
