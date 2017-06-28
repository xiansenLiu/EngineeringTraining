package com.example.server.controllers;

import com.example.server.services.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author       xinliu
 * Date         6/28/17
 * Time         1:46 PM
 */
@RestController
public class LicensePlateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LicensePlateController.class);

    @Autowired
    private StorageService storageService;

    @PostMapping("/recognize")
    public void recognize(@RequestParam("image") MultipartFile image) {
        LOGGER.info(image.getOriginalFilename());
        storageService.store(image);
    }
}
