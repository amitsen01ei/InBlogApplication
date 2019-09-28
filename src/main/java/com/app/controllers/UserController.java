package com.app.controllers;

import com.app.dtos.request.UserStatusChangeRequestDTO;
import com.app.services.UserService;
import com.app.utils.BaseResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/a")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public BaseResponse getAllUsersByActiveStatus(@RequestHeader(name = "token") String token, Boolean isActive,
                                                  Pageable pageable) {
        return BaseResponse.builder()
                .code(Integer.toString(HttpStatus.OK.value()))
                .message(List.of("OK"))
                .result(userService.getAllUsersByActiveStatus(token, isActive, pageable))
                .build();
    }

    @PostMapping("/user/change-status")
    public BaseResponse changeUserStatus (@RequestHeader(name = "token") String token,
                                          @RequestBody UserStatusChangeRequestDTO userStatusChangeRequestDTO) {

        userService.changeUserStatus(token, userStatusChangeRequestDTO);
        return BaseResponse.builder()
                .code(Integer.toString(HttpStatus.OK.value()))
                .message(List.of("OK"))
                .build();
    }
}
