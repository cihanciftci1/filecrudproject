package com.project.filecrud.controller;

import com.project.filecrud.manager.AuthManager;
import com.project.filecrud.model.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthManager authManager;

    @PostMapping
    public BaseResponse register(){

        return null;
    }


}
