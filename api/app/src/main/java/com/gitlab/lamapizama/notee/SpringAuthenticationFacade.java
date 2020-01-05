package com.gitlab.lamapizama.notee;

import com.gitlab.lamapizama.notee.commons.authentication.AuthenticationFacade;
import com.gitlab.lamapizama.notee.commons.authentication.UserDetails;
import io.vavr.control.Option;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpringAuthenticationFacade implements AuthenticationFacade {

    @Override
    public Option<UserDetails> getUserDetails() {
        return Option.of(SecurityContextHolder.getContext().getAuthentication())
                .map(authentication -> new UserDetails(authentication.getName()));
    }

    @Override
    public String getCurrentUserEmail() {
        return getUserDetails()
                .map(UserDetails::getUserId)
                .getOrElseThrow(() -> new IllegalStateException("No user currently authenticated"));
    }

    @Override
    public boolean isActionAllowedFor(String commanderEmail) {
        return getUserDetails()
                .map(UserDetails::getUserId)
                .filter(userId -> userId.equals(commanderEmail))
                .isDefined();
    }

    @Override
    public boolean isActionAllowedFor(String commanderEmail, List<String> userFriendsEmails) {
        return isActionAllowedFor(commanderEmail) || userFriendsEmails.stream()
                .anyMatch(friendEmail -> friendEmail.equals(commanderEmail));
    }
}
