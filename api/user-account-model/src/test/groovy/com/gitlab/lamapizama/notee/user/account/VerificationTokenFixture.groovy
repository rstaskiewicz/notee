package com.gitlab.lamapizama.notee.user.account

import com.gitlab.lamapizama.notee.user.verification.TokenExpiration
import com.gitlab.lamapizama.notee.user.verification.Token
import com.gitlab.lamapizama.notee.user.verification.VerificationToken

import java.time.Duration
import java.time.Instant

import static java.time.Instant.now

class VerificationTokenFixture {

    static final Instant TOMORROW = now() + Duration.ofDays(1)
    static final Instant YESTERDAY = now() - Duration.ofDays(1)

    static VerificationToken verificationToken() {
        return verificationToken(anyToken())
    }

    static VerificationToken expiredVerificationToken() {
        return new VerificationToken(anyToken(), new TokenExpiration(YESTERDAY))
    }

    static VerificationToken verificationToken(Token token) {
        return new VerificationToken(token, new TokenExpiration(TOMORROW))
    }

    static Token anyToken() {
        return new Token(UUID.randomUUID().toString())
    }
}
