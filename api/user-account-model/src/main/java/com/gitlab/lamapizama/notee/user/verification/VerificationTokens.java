package com.gitlab.lamapizama.notee.user.verification;


import io.vavr.control.Option;

public interface VerificationTokens {

    Option<VerificationToken> findBy(Token token);
}
