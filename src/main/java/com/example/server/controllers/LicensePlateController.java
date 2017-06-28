package com.example.server.controllers;

import com.example.server.domain.Image;
import com.example.server.domain.Result;
import com.example.server.services.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

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
    public Result recognize(@RequestParam("image") MultipartFile image) {
        LOGGER.info(image.getOriginalFilename());
        String result = storageService.store2cache(image);
        LOGGER.info(result);
        return new Result("success", "success", result, result);
    }

    @GetMapping(value = "/similar")
    public List<Image> findSimilar(@RequestParam("plate_number") String number) {
        LOGGER.info("get images similar to that has plate number :" + number);
        List<Image> images = storageService.getSimilar(number)
                .map(name -> new Image(name, number, name))
                .collect(Collectors.toList());
        return images;
    }

    @GetMapping("/image")
    public ResponseEntity<Resource> getImage(@RequestParam("plate_number") String plate_number, @RequestParam("name") String name) {
        Resource resource = storageService.load(plate_number, name);
        HttpStatus status;
        if (resource == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }
        return ResponseEntity
                .status(status)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + name + "\"")
                .body(resource);
    }
}
