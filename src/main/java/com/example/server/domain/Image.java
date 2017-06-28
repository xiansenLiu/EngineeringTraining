package com.example.server.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Author       xinliu
 * Date         6/28/17
 * Time         4:32 PM
 */
public class Image {
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("plate_number")
    private String plateNumber;
    @Expose
    @SerializedName("md5")
    private String md5;

    public Image() {
    }

    public Image(String name, String plateNumber, String md5) {
        this.name = name;
        this.plateNumber = plateNumber;
        this.md5 = md5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
