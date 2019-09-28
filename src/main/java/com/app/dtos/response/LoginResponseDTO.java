package com.app.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
    String accessToken;
    String role;
    String userId;
    String username;
}
