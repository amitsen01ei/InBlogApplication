package com.app.utils;

import com.app.constants.ErrorMessage;
import com.app.models.User;
import com.app.services.JwtTokenService;
import com.app.services.UserService;
import com.app.utils.exceptions.InBlogAppException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class InBlogUtil {

    private final JwtTokenService jwtTokenService;
    private final UserService userService;


    public InBlogUtil(JwtTokenService jwtTokenService, UserService userService) {
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
    }

    public User getUserFromToken (String token) throws InBlogAppException {
        Map<String,String> requestingUserDataMap = jwtTokenService.getUserDataFromToken(token);

        Optional<User> optionalUser = userService.findByUsername(requestingUserDataMap.get("USER_NAME"));

        return optionalUser.orElseThrow(() -> InBlogResponseUtil.throwApplicationException(ErrorMessage.INVALID_TOKEN,
                null));
    }
}
