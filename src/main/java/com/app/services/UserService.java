package com.app.services;

import com.app.dtos.request.UserStatusChangeRequestDTO;
import com.app.dtos.response.PageableResponseDTO;
import com.app.dtos.response.UserResponseDTO;
import com.app.models.User;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername (String username);

    void save(User newUser);

    PageableResponseDTO<UserResponseDTO> getAllUsersByActiveStatus(String token, Boolean isActive, Pageable pageable);

    void changeUserStatus (String token, UserStatusChangeRequestDTO requestDTO);
}
