package com.app.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class UserContext {
    private final String username;
    private final Integer userId;

    public static UserContext create(String username, Integer userId) {
        if (StringUtils.isBlank(username)) throw new IllegalArgumentException("Username is blank: " + username);
        return new UserContext(username, userId);
    }

}
