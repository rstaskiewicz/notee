package com.gitlab.lamapizama.notee.user.account.validation;

import com.gitlab.lamapizama.notee.user.account.request.RegisterUserAccountRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        final RegisterUserAccountRequest request = (RegisterUserAccountRequest) value;
        return request.getPassword().equals(request.getMatchingPassword());
    }
}
