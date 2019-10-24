package com.gitlab.lamapizama.notee.user.verification;

import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

@Value
public class VerificationToken {

    @NonNull Token token;
    @NonNull TokenExpiration expiration;

    public boolean isExpired() {
        return expiration.getWhen().isBefore(Instant.now());
    }
}
