package com.project.filecrud.controller;

import com.project.filecrud.manager.AuthManager;
import com.project.filecrud.model.request.AuthRequest;
import com.project.filecrud.model.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthManager authManager;

    @PostMapping
    public ResponseEntity<BaseResponse> register(@Valid @RequestBody AuthRequest authRequest){
        log.info("Auth Controller register starts with | username : {}", authRequest.getUsername());
        BaseResponse response = authManager.register(authRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BaseResponse> login(@Valid @RequestBody AuthRequest authRequest){
        log.info("Auth Controller login starts with | username : {}", authRequest.getUsername());
        BaseResponse response = authManager.login(authRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
