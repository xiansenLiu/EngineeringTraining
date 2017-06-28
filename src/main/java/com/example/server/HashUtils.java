package com.example.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Author       xinliu
 * Date         6/28/17
 * Time         2:32 PM
 */
public class HashUtils {
    public static final String MD2 = "MD2";
    public static final String MD5 = "MD5";
    public static final String SHA_1 = "SHA-1";
    public static final String SHA_256 = "SHA-256";
    public static final String SHA_384 = "SHA-384";
    public static final String SHA_512 = "SHA-512";


    public static String fileMD5(String path) {
        if (path == null || path.trim().equals("")) {
            return null;
        }
        return fileMD5(new File(path));
    }

    public static String fileMD5(File file) {
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            System.out.println("invalid file");
            return null;
        }
        String result = null;
        DigestInputStream dis = null;
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance(MD5);
            dis = new DigestInputStream(new FileInputStream(file), md);
            byte[] buffer = new byte[1024 * 25];
            while (dis.read(buffer) > 0) {
                md = dis.getMessageDigest();
            }
            BigInteger bi = new BigInteger(1, md.digest());
            result = bi.toString(16);
            return result;
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String stringMD5(String input) {
        String result = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] inputByteArray = input.getBytes();
            md5.update(inputByteArray);
            BigInteger bi = new BigInteger(1, md5.digest());
            result = bi.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
