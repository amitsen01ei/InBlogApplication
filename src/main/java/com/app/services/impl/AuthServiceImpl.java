package com.app.services.impl;

import com.app.constants.ErrorMessage;
import com.app.constants.enums.Roles;
import com.app.dtos.request.LoginRequestDTO;
import com.app.dtos.request.SignupRequestDTO;
import com.app.dtos.response.LoginResponseDTO;
import com.app.dtos.security.AuthenticationRequest;
import com.app.dtos.security.TokenDTO;
import com.app.models.User;
import com.app.services.AuthService;
import com.app.services.JwtTokenService;
import com.app.services.UserService;
import com.app.utils.InBlogResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    @Autowired
    public AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDto) {
        Optional<User> userModel = userService.findByUsername(loginRequestDto.getUsername());
        var user = userModel
                .orElseThrow(() -> InBlogResponseUtil.throwApplicationException(ErrorMessage.USER_NOT_FOUND, null));

        if (!user.getIsActive()) {
            InBlogResponseUtil.throwApplicationException(ErrorMessage.USER_NOT_ACTIVE, null);
        }

        if (user.getRole() != getRole(loginRequestDto.getIsAdmin())) {
            InBlogResponseUtil.throwApplicationException(ErrorMessage.USER_NOT_FOUND, null);
        }

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            InBlogResponseUtil.throwApplicationException(ErrorMessage.INVALID_PASSWORD, null);
        }

        var authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername(user.getUsername());
        authenticationRequest.setPassword(user.getPassword());

        TokenDTO tokenDTO = jwtTokenService.getTokens(authenticationRequest, user);

        return LoginResponseDTO.builder()
                .accessToken(tokenDTO.getAccessToken().getToken())
                .role(Integer.toString(user.getRole()))
                .userId(Integer.toString(user.getId()))
                .username(user.getUsername())
                .build();
    }

    @Override
    public void signUp(SignupRequestDTO signupRequestDTO) {

        User newUser = new User ();
        newUser.setUsername(signupRequestDTO.getUserName());
        newUser.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        newUser.setRole(getRole(signupRequestDTO.getIsAdmin()));
        newUser.setEmail(signupRequestDTO.getEmail());
        newUser.setFullName(signupRequestDTO.getFullName());
        newUser.setIsActive(Boolean.valueOf(signupRequestDTO.getIsAdmin().toLowerCase()));
        newUser.setCreatedOn(new Date());

        userService.save(newUser);
    }

    private int getRole(String isAdmin) {
        if (StringUtils.isBlank(isAdmin) || isAdmin.equalsIgnoreCase(Boolean.FALSE.toString())) {
            return Roles.BLOGGER.getRoleId();
        }
        else if (isAdmin.equalsIgnoreCase(Boolean.TRUE.toString())) {
            return Roles.ADMIN.getRoleId();
        }
        else {
            InBlogResponseUtil.throwApplicationException("Invalid Argument for role", null);
        }
        return -1;
    }
}
