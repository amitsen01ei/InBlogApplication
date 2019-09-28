package com.app.dtos.request;

import lombok.Data;

@Data
public class UserStatusChangeRequestDTO {
    private String username;
    private String isActive;
}
