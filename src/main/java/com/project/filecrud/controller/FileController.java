package com.project.filecrud.controller;

import com.project.filecrud.manager.FileManager;
import com.project.filecrud.model.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
@Slf4j
public class FileController {
    private final FileManager fileManager;

    // TODO log ekle , spring.jpa.hibernate.ddl-auto kontrol et

    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestParam(name = "file")MultipartFile file){
        log.info("File controller, create is starts with | fileName {}", file.getName());
        BaseResponse response = fileManager.save(file);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
