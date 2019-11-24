package com.gitlab.lamapizama.notee.commons.authentication;

import io.vavr.control.Option;

public interface AuthenticationFacade {

    Option<UserDetails> getUserDetails();
}
