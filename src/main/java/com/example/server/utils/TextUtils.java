package com.example.server.utils;

import java.util.regex.Pattern;

/**
 * Author       xinliu
 * Date         6/29/17
 * Time         9:58 PM
 */
public class TextUtils {
    private static final String PATTERN = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";

    public static boolean isPlateNumber(String plateNumber) {
        return Pattern.matches(PATTERN, plateNumber);
    }
}
