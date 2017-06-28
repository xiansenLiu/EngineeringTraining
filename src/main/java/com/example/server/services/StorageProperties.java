package com.example.server.services;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Author       xinliu
 * Date         6/28/17
 * Time         2:02 PM
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    private String cacheDir = "cache";

    private String picDir = "pic";


    public String getCacheDir() {
        return cacheDir;
    }

    public void setCacheDir(String cacheDir) {
        this.cacheDir = cacheDir;
    }

    public String getPicDir() {
        return picDir;
    }

    public void setPicDir(String picDir) {
        this.picDir = picDir;
    }
}
