package com.gitlab.lamapizama.notee.user.account.request;

import com.gitlab.lamapizama.notee.user.account.validation.EmailNotUsed;
import com.gitlab.lamapizama.notee.user.account.validation.PasswordMatches;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterUserAccountRequest {

    @NotNull
    @Email
    @EmailNotUsed
    String email;

    @Size(min = 4, max = 32)
    @NotBlank
    String username;

    @Size(min = 4)
    @NotBlank
    String password;

    String matchingPassword;
}
