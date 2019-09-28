package com.app.services;

import com.app.dtos.request.LoginRequestDTO;
import com.app.dtos.request.SignupRequestDTO;
import com.app.dtos.response.LoginResponseDTO;

public interface AuthService {

    LoginResponseDTO login (LoginRequestDTO loginRequestDto);

    void signUp (SignupRequestDTO signupRequestDTO);
}
