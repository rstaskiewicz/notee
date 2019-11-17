package com.gitlab.lamapizama.notee.user.account.validation;

import com.gitlab.lamapizama.notee.user.account.UserAccounts;
import com.gitlab.lamapizama.notee.user.account.UserEmail;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class EmailNotUsedValidator implements ConstraintValidator<EmailNotUsed, String> {

    private final UserAccounts userAccounts;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userAccounts.containsAccountWith(new UserEmail(email));
    }
}
