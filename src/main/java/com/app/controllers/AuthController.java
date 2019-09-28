package com.app.controllers;

import com.app.dtos.request.LoginRequestDTO;
import com.app.dtos.request.SignupRequestDTO;
import com.app.services.AuthService;
import com.app.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@Validated
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController (AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/p/login")
    public BaseResponse login (@RequestBody LoginRequestDTO request) {
        System.out.println("Hit");
        return BaseResponse.builder()
                .result(authService.login(request))
                .code(Integer.toString(HttpStatus.OK.value()))
                .message(List.of("OK"))
                .build();
    }

    @PostMapping("/p/signup")
    public BaseResponse signUp (@Valid @RequestBody SignupRequestDTO request) {
        authService.signUp(request);
        return BaseResponse.builder()
                .code(Integer.toString(HttpStatus.OK.value()))
                .message(List.of("OK"))
                .result(Collections.emptyList())
                .build();
    }
}
