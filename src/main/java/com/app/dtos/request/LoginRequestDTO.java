package com.app.dtos.request;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
    private String isAdmin;
}
