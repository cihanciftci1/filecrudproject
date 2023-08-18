package com.project.filecrud.controller;

import com.project.filecrud.manager.FileManager;
import com.project.filecrud.model.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
@Slf4j
public class FileController {
    private final FileManager fileManager;

    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestParam(name = "file") MultipartFile file){
        log.info("File controller, file save starts with | fileName {}", file.getName());
        BaseResponse response = fileManager.save(file);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> retrieve(@PathVariable("id") Integer id){
        log.info("File controller, retrieve file starts with | id : {}", id);
        BaseResponse response = fileManager.retrieve(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Integer id, @RequestParam(name = "file") MultipartFile file){
        log.info("File controller, file update starts with | id : {}, fileName : {}", id, file.getName());
        BaseResponse response = fileManager.update(id, file);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") Integer id){
        log.info("File controller, delete file starts with | id : {}", id);
        BaseResponse response = fileManager.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


//TODO unit test -> swagger -> docker -> postman -> github readme