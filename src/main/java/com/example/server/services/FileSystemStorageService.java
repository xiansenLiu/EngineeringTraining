package com.example.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
    public Path store2cache(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        Path targetFile = null;
        try {
            targetFile = cacheDir.resolve(file.getOriginalFilename());
            if (Files.exists(targetFile)) {
                Files.delete(targetFile);
            }
            Files.copy(file.getInputStream(), targetFile);
            return targetFile;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                Files.delete(targetFile);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void move2dir(File file, String dirName) {

    }

    @Override
    public Stream<String> getSimilar(String plateNumber) {
        try {
            Path targetDir = this.picDir.resolve(plateNumber);
            if (!Files.exists(targetDir) && !Files.isDirectory(targetDir)) {
                return Stream.empty();
            }
            return Files.walk(targetDir, 1)
                    .filter(path -> !path.equals(targetDir))
                    .map(path -> targetDir.relativize(path).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.empty();
    }

    @Override
    public Resource load(String dir, String name) {
        Path targetDir = this.picDir.resolve(dir);
        Path targetFile = targetDir.resolve(name);
        LOGGER.info(targetFile.toString());
        try {
            UrlResource resource = new UrlResource(targetFile.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                LOGGER.info("no such file or this file is not readable");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean move2picDir(Path source, String dir, String name) {
        Path targetDir = this.picDir.resolve(dir);
        Path targetFile = targetDir.resolve(name);
        try {
            if (!Files.exists(targetDir)) {
                Files.createDirectory(targetDir);
            }
            if (Files.exists(targetFile)) {
                Files.delete(targetFile);
            }
            Files.move(source, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(cacheDir.toFile());
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
