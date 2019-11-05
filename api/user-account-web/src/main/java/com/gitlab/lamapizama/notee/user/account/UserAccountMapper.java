package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.user.account.request.RegisterUserAccountRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
class UserAccountMapper {

    RegisterUserAccountCommand fromCreate(RegisterUserAccountRequest request) {
        return RegisterUserAccountCommand.builder()
                .email(new UserEmail(request.getEmail()))
                .username(new Username(request.getUsername()))
                .password(new RawPassword(request.getPassword()))
                .contextPath(new RegistrationContextPath(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString()))
                .build();
    }
}
