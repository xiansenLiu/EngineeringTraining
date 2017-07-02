package com.example.server.controllers;

import com.example.server.domain.ErrorRecognizeResponse;
import com.example.server.domain.RecognizeResult;
import com.example.server.domain.RecognizeResponse;
import com.example.server.repository.RecognizeResultRepository;
import com.example.server.services.StorageService;
import com.example.server.utils.HashUtils;
import com.example.server.utils.ImageRecognizer;
import com.example.server.utils.ImageUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Arrays;

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
    @Autowired
    private RecognizeResultRepository resultRepository;


    @PostMapping("/recognize")
    public RecognizeResponse recognize(@RequestParam("image") MultipartFile imageFile) {
        RecognizeResponse response = null;
        String state;
        int code;

        Path file = storageService.store2cache(imageFile);
        if (file == null || !ImageUtils.isValidImage(file.toFile())) {
            state = RecognizeResponse.STATE_ERROR;
            code = RecognizeResponse.CODE_INVALID_IMAGE;
            response = new RecognizeResponse(state, code, Arrays.asList());
        } else {

            String md5 = HashUtils.fileMD5(file.toFile());

            RecognizeResult recognizeResult = resultRepository.findByMd5(md5);
            String result;
            if (recognizeResult != null) {
                result = recognizeResult.getResult();
                if (result != null) {
                    response = parseResponse(result);
                }
            } else {
                result = ImageRecognizer.recognize(file.toFile());
                if (result != null) {
                    recognizeResult = new RecognizeResult(md5, result.trim());
                    resultRepository.save(recognizeResult);
                    response = parseResponse(result);
                } else {
                    response = new RecognizeResponse(RecognizeResponse.STATE_ERROR, RecognizeResponse.CODE_INTERNAL_ERROR, Arrays.asList());
                }
            }
        }
        return response;
    }

    //schema
//    @GetMapping("/image")
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

    private static RecognizeResponse parseResponse(String json) {
        RecognizeResponse response;
        try {
            response = new Gson().fromJson(json, RecognizeResponse.class);
        } catch (JsonSyntaxException|IllegalStateException e) {
//            e.printStackTrace();
            ErrorRecognizeResponse errorResponse = new Gson().fromJson(json, ErrorRecognizeResponse.class);
            response = new RecognizeResponse(errorResponse.getStatus(), errorResponse.getCode(), Arrays.asList(errorResponse.getResult()));
        }
        return response;
    }
}
