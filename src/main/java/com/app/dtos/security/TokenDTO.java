package com.app.dtos.security;

import lombok.Data;

@Data
public class TokenDTO {
    private JwtToken accessToken;
}
