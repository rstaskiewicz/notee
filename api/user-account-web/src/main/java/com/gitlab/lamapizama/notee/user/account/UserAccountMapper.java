package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.user.account.dto.UserAccountCreateDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
class UserAccountMapper {

    RegisterUserAccountCommand fromCreate(UserAccountCreateDto dto) {
        return RegisterUserAccountCommand.builder()
                .email(new UserEmail(dto.getEmail()))
                .username(new Username(dto.getUsername()))
                .password(new RawPassword(dto.getPassword()))
                .contextPath(new RegistrationContextPath(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString()))
                .build();
    }
}
