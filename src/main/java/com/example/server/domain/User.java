package com.example.server.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Author       xinliu
 * Date         6/28/17
 * Time         2:39 PM
 */
public class User {
    @Expose
    @SerializedName("id")
    private Long id;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("pwd")
    private String pwd;

    public User() {
    }

    public User(Long id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
