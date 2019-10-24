package com.gitlab.lamapizama.notee.user.verification;

import lombok.Value;

import java.time.Duration;
import java.time.Instant;

@Value
public class TokenExpiration {

    static Duration DURATION_BEFORE_EXPIRATION = Duration.ofDays(1);

    Instant when;

    public TokenExpiration(Instant when) {
        this.when = when;
    }

    public static TokenExpiration forTokenCreatedOn(Instant tokenCreation) {
        return new TokenExpiration(tokenCreation.plus(DURATION_BEFORE_EXPIRATION));
    }
}
