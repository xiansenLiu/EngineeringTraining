package com.example.server.utils;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Author       xinliu
 * Date         6/29/17
 * Time         8:01 PM
 */
public class ImageRecognizer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageRecognizer.class);
    private static final String API = "http://119.29.218.79:8080/recognize";

    public static String recognize(File image) {

        OkHttpClient client = new OkHttpClient.Builder().build();
        RequestBody body = RequestBody.create(ImageUtils.parseImageType(image), image);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("img", image.getName(), body)
                .build();
        Request request = new Request.Builder()
                .url(API)
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
