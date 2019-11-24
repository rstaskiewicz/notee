package com.gitlab.lamapizama.notee;

import com.gitlab.lamapizama.notee.commons.authentication.AuthenticationFacade;
import com.gitlab.lamapizama.notee.commons.authentication.UserDetails;
import io.vavr.control.Option;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringAuthenticationFacade implements AuthenticationFacade {

    @Override
    public Option<UserDetails> getUserDetails() {
        return Option.of(SecurityContextHolder.getContext().getAuthentication())
                .map(authentication -> new UserDetails(authentication.getName()));
    }
}
