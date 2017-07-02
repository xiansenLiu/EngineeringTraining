package com.example.server.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;

/**
 * Author       xinliu
 * Date         7/2/17
 * Time         10:25 AM
 */
@Entity
@Table(name = "engineering_result")
public class RecognizeResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    @SerializedName("id")
    private Long id;

    private String md5;
    private String result;

    public RecognizeResult() {
    }

    public RecognizeResult(String md5) {
        this.md5 = md5;
    }

    public RecognizeResult(String md5, String result) {
        this.md5 = md5;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
