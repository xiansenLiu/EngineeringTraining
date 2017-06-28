package com.example.server.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Author       xinliu
 * Date         6/28/17
 * Time         1:50 PM
 */
public interface StorageService {

    boolean store(MultipartFile file);

    void deleteAll();

    Stream<Path> getSame(String path);

    void init();
}
