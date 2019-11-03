package com.gitlab.lamapizama.notee.user.auth.service;

import com.gitlab.lamapizama.notee.user.auth.dto.DefaultUserDao;
import com.gitlab.lamapizama.notee.user.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class NoteeUserDetailsService implements UserDetailsService {

    private final DefaultUserDao userDto;

    private static boolean ACCOUNT_NON_EXPIRED = true;
    private static boolean CREDENTIALS_NON_EXPIRED = true;
    private static boolean ACCOUNT_NON_BLOCKED = true;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDto.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exists: " + email));

        final UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isConfirmed(),
                ACCOUNT_NON_EXPIRED,
                CREDENTIALS_NON_EXPIRED,
                ACCOUNT_NON_BLOCKED,
                Collections.emptyList());

        return userDetails;
    }
}
