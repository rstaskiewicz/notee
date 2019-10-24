package com.gitlab.lamapizama.notee.user.account.validation;

import com.gitlab.lamapizama.notee.user.account.dto.UserAccountCreateDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        final UserAccountCreateDto dto = (UserAccountCreateDto) value;
        return dto.getPassword().equals(dto.getMatchingPassword());
    }
}
