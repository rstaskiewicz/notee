package com.gitlab.lamapizama.notee.user.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserBCryptPasswordEncoder implements EncodeUserPassword {

    private final PasswordEncoder passwordEncoder;

    @Override
    public EncodedPassword encode(RawPassword rawPassword) {
        return new EncodedPassword(passwordEncoder.encode(rawPassword.getPassword()));
    }
}
