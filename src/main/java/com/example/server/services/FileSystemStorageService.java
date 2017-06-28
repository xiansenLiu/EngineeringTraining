package com.example.server.services;

import com.example.server.HashUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Author       xinliu
 * Date         6/28/17
 * Time         2:01 PM
 */
@Service
public class FileSystemStorageService implements StorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemStorageService.class);
    private Path cacheDir;
    private Path picDir;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.cacheDir = Paths.get(properties.getCacheDir());
        this.picDir = Paths.get(properties.getPicDir());
        init();
    }

    @Override
    public boolean store(MultipartFile file) {
        if (file.isEmpty()) {
            return false;
        }
        Path targetFile = null;
        try {
            String filename = file.getOriginalFilename();
            targetFile = cacheDir.resolve(HashUtils.stringMD5(filename));
            if (targetFile.toFile().exists()) {
                LOGGER.info(filename + " exists , delete it");
                Files.delete(targetFile);
            }
            Files.copy(file.getInputStream(), targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                Files.delete(targetFile);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return false;
        }
        return true;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(cacheDir.toFile());
    }

    @Override
    public Stream<Path> getSame(String plateNumber) {

        return null;
    }

    @Override
    public void init() {
        try {
            boolean exists = Files.exists(this.cacheDir);
            if (!exists) {
                LOGGER.info("create cache directory for the first time");
                Files.createDirectory(this.cacheDir);
            } else {
                LOGGER.info("cache directory has already been created");
            }
            exists = Files.exists(this.picDir);
            if (!exists) {
                LOGGER.info("pic cache directory for the first time");
                Files.createDirectory(this.picDir);
            } else {
                LOGGER.info("pic directory has already been created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
