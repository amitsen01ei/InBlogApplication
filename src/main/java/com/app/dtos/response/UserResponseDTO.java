package com.app.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {

    private String username;

    private String email;

    private String fullName;

    private String role;

    private Boolean isActive;
}
