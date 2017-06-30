package ru.javawebinar.topjava.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.UserTo;

/**
 * Created by butkoav on 26.06.2017.
 */
@Component
public class UserFormValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserTo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "email", "validation.isEmpty");

        UserTo user = (UserTo) o;
        if (AuthorizedUser.safeGet() != null) user.setId(AuthorizedUser.id());

        if (!userService.isEmailOk(user))
            errors.rejectValue("email", "validation.email.AlredyExist");

    }
}
