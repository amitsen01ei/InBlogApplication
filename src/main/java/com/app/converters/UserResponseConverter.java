package com.app.converters;

import com.app.dtos.response.UserResponseDTO;
import com.app.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserResponseConverter implements Converter<User, UserResponseDTO> {
    @Override
    public UserResponseDTO convert(User source) {
        return UserResponseDTO.builder()
                .username(source.getUsername())
                .email(source.getEmail())
                .fullName(source.getFullName())
                .role(Integer.toString(source.getRole()))
                .isActive(source.getIsActive())
                .build();
    }
}
