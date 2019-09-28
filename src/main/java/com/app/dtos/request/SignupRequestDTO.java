package com.app.dtos.request;

import com.app.validators.UniqueUsername;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class SignupRequestDTO {

    @NotEmpty(message = "username cannot be empty")
    @UniqueUsername
    private String userName;

    private String isAdmin;

    @NotEmpty(message = "username cannot be empty")
    private String fullName;

    @Email(message = "Invalid Email")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Password field cannot be empty")
    private String password;

}
