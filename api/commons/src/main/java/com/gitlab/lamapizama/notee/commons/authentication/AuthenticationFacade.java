package com.gitlab.lamapizama.notee.commons.authentication;

import io.vavr.control.Option;

import java.util.List;

public interface AuthenticationFacade {

    Option<UserDetails> getUserDetails();

    String getCurrentUserEmail();

    boolean isActionAllowedFor(String commanderEmail);

    boolean isActionAllowedFor(String commanderEmail, List<String> userFriendsEmails);
}
