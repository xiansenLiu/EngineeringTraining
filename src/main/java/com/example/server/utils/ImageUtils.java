package com.example.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Author       xinliu
 * Date         6/28/17
 * Time         3:13 PM
 */
public class ImageUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(ImageUtils.class);


    private static final String IMAGE_TYPE_JPEG = "JPEG";
    private static final String IMAGE_TYPE_PNG = "png";

    public static boolean isImage(String path) {
        if (path == null || path.trim().equals("")) {
            return false;
        }
        return isImage(new File(path));
    }


    public static boolean isImage(File file) {
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            System.out.println("invalid file");
            return false;
        }
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
            if (image == null || image.getWidth(null) < 0 || image.getHeight(null) < 0) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        image = null;
        return true;
    }

    public static String getImageType(File file) {
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(file);
            Iterator<ImageReader> iReaders = ImageIO.getImageReaders(iis);
            if (iReaders.hasNext()) {
                ImageReader reader = iReaders.next();
                return reader.getFormatName();
            }
        } catch (IOException e) {
            LOGGER.error(file.getName() + " is not a image");
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isValidImage(String path) {
        if (path == null || path.trim().equals("")) {
            return false;
        }
        return isValidImage(new File(path));
    }
    public static boolean isValidImage(File file) {
        String imageType = getImageType(file);
        if (imageType == null) {
            return false;
        }
        if (imageType.equals(IMAGE_TYPE_JPEG) || imageType.equals(IMAGE_TYPE_PNG)) {
            return true;
        }
        return false;
    }

    private ImageUtils() {
    }
}
