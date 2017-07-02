package com.example.server.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Author       xinliu
 * Date         6/28/17
 * Time         1:50 PM
 */
public interface StorageService {

    Path store2cache(MultipartFile file);

    void move2dir(File file, String dir);

    Stream<String> getSimilar(String path);

    Resource load(String dir, String name);


    boolean move2picDir(Path source, String dir, String name);

    void deleteAll();

    void init();
}
