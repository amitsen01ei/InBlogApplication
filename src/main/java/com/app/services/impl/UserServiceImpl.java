package com.app.services.impl;

import com.app.constants.ErrorMessage;
import com.app.constants.enums.Roles;
import com.app.converters.PageableResponseConverter;
import com.app.converters.UserResponseConverter;
import com.app.dtos.request.UserStatusChangeRequestDTO;
import com.app.dtos.response.PageableResponseDTO;
import com.app.dtos.response.UserResponseDTO;
import com.app.models.User;
import com.app.repositories.UserRepository;
import com.app.services.JwtTokenService;
import com.app.services.UserService;
import com.app.utils.InBlogResponseUtil;
import com.app.utils.exceptions.InBlogAppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserResponseConverter userResponseConverter;
    private final JwtTokenService jwtTokenService;

    public UserServiceImpl(UserRepository userRepository, UserResponseConverter userResponseConverter,
                           JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.userResponseConverter = userResponseConverter;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save (User user) {
        userRepository.save(user);
    }

    @Override
    public PageableResponseDTO<UserResponseDTO> getAllUsersByActiveStatus(String token, Boolean isActive,
                                                                          Pageable pageable) {

        var user = getUserFromToken(token);

        if (Roles.ADMIN.getRoleId() != user.getRole()) {
            InBlogResponseUtil.throwApplicationException(ErrorMessage.UNAUTHORIZED, null);
        }

        Page<User> inactiveUsers = userRepository.findAllByIsActive(isActive, pageable);
        var pageableResponseConverter = new PageableResponseConverter<User, UserResponseDTO>();

        return pageableResponseConverter.convert(inactiveUsers, userResponseConverter);
    }

    @Override
    public void changeUserStatus (String token, UserStatusChangeRequestDTO requestDTO) {

        var requestingUser = getUserFromToken(token);

        if (Roles.ADMIN.getRoleId() != requestingUser.getRole()) {
            InBlogResponseUtil.throwApplicationException(ErrorMessage.UNAUTHORIZED, null);
        }

        var userName = requestDTO.getUsername();

        Optional<User> optionalUser = userRepository.findByUsername(userName);

        var user = optionalUser.orElseThrow(() -> InBlogResponseUtil.throwApplicationException(
                ErrorMessage.USER_NOT_FOUND, null));

        user.setIsActive(Boolean.valueOf(requestDTO.getIsActive().toLowerCase()));

        userRepository.save(user);
    }

    private User getUserFromToken (String token) throws InBlogAppException {
        Map<String,String> requestingUserDataMap = jwtTokenService.getUserDataFromToken(token);

        Optional<User> optionalUser = findByUsername(requestingUserDataMap.get("USER_NAME"));

        return optionalUser.orElseThrow(() -> InBlogResponseUtil.throwApplicationException(ErrorMessage.INVALID_TOKEN,
                                                                                                        null));
    }
}
