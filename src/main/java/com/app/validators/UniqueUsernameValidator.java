package com.app.validators;

import com.app.models.User;
import com.app.services.UserService;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserService userService;

    private UniqueUsername uniqueUsername;

    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        this.uniqueUsername = constraintAnnotation;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        Optional<User> user = userService.findByUsername(username);

        if (user.isPresent()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    uniqueUsername.message()).addConstraintViolation();

            return false;
        }

        return true;
    }
}
